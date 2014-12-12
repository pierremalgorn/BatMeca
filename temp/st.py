#!/usr/bin/python 
# -*- coding: utf-8 -*-

import sys
import os
import string
import copy
import math
import numpy as np
import scipy
from scipy import stats
import matplotlib.pyplot as plt

def LoadF(name) :
# formatage du fichier source en fichier .prn 
   replace_virgule_point(name)
# extraction du tableau de données à partir  
   finp=name.split('.')[0]+'.prn'
   data=np.loadtxt(finp)
   return data

def replace_virgule_point(inp) :
      finp = open(inp,'r')
      lines= finp.readlines()
      finp.close()
      items=inp.split(".")
      out=items[0]+".prn"
      fout=open(out,'w')
      for line in lines :
         line=line.replace(',','.')
         items=line.split()
         try :
            if len(items)==0 : continue
            x = float(items[0])
         except :
            line = "#  "+line
         fout.writelines(line)
      fout.close()

def error() :
   print """
   Traitement d'un ou plusieurs fichiers de données de traction simple
   syntax: file.par 
   file : fichier des paramètres d'entrée 
   """
   sys.exit(0)

def make_dict(lines) :
  i=0
  for line in lines :
    if line[0]=='#' : continue
    items = line.split()
    if len(items) == 0 : continue
    if len(items) == 1 : print "%s ?? at line %i"  % (items[0],i+1)
    if Dict.has_key(items[0]) == False :
         Dict[ items[0] ] = []
    Dict[ items[0] ].append( items[1:] )
    i+=1

# Lissage des données sous forme de moyenne mobile
def moving_average(data,args) :
   freq=  1
   for i in range (0,len(args)/2) :
         arg=args[2*i]
         val=args[2*i+1]
         if arg == "freq" : freq =int(val)
         elif arg == "Rfreq" : freq = int(len(data[1:])*float(val))
   if freq <= 0 : freq = 1
   nblig=len(data[:,0])
   nbcol=len(data[0,:])
   res=np.zeros((nblig-freq,nbcol))
   for i in range(len(res[:,0])) :
      try :
	 x=data[i+freq:,];y=data[i:i+freq,]
	 res[i,]=y.mean(0)
      except :
         pass
   return res

def find_mins(Fs) :
    mins=[]
    for i in range(search,len(Fs)-search) :
        X=Fs[i]
        ok=True
        for j in range(-search,search) :
           x = Fs[i+j]
           if x < X : ok=False; break
        if ok == True : mins=np.concatenate((mins,[i])) 
    mins=np.array(mins, dtype='int')
    return mins
    
def find_maxs(Fs) :
    maxs=[]
    for i in range(search,len(Fs)-search) :
        X=Fs[i]
        ok=True
        for j in range(-search,search) :
           x = Fs[i+j]
           if x > X : ok=False; break
        if ok == True : maxs=np.concatenate((maxs,[i])); 
    maxs=np.array(maxs, dtype='int')
    return maxs

def getslope(eps,sig,sig1,sig2) :
   u=[];f=[]
   for index in range(len(sig)) :
      y=sig[index] 
      if (y > sig1) and (y < sig2) :
         x=eps[index]
         u.append(x)
         f.append(y)
   slope = stats.linregress(u,f)[0]
   shift = stats.linregress(f,u)[1]
   return slope,shift

def apply_suppress(data,args) :
   fr =0 
   to = len(data)-1
   for i in range (0,len(args)/2) :
       var=args[2*i]
       val =args[2*i+1]
       if var == "from": fr  = int(val)
       elif var == "to"  : to  = int(val)
       else              : print "apply_suppress %s" % (var); sys.exit(0)
   data=np.concatenate((data[0:fr,:],data[to+1:,:]))
   return data

def apply_factor(data,args) :
   col =0
   val = 1 
   for i in range (0,len(args)/2) :
       var=args[2*i]
       val =args[2*i+1]
       if var == "col": col  = int(val)-1
       elif var == "val"  : factor  = int(val)
       else              : print "apply_factor %s" % (var); sys.exit(0)
   data[:,col]=data[:,col]*factor
   return data


def apply(data,when) :
   try :
       if when=="before" : apps = Dict[ "apply" ]
       elif when=="after" : apps = Dict[ "p_apply" ]
   except :
      return data
   for app in apps :
      if   app[0] == "shift"    : data=apply_shift(data,app[1:])
      elif app[0] == "suppress" : data=apply_suppress(data,app[1:])
      elif app[0] == "factor"   : data=apply_factor(data,app[1:])
      elif app[0] == "moving_average" : data=moving_average(data,app[1:])
      else                    : print "Error: apply %s ???" % (app[0]); sys.exit(0)
   return data

def filtre_load_drop(F,max) :
   Fmax=0.
   Fmax=F[max];

   for i in range(max,len(F)) :
        dF = (F[i]-F[i-1])
        if dF < -Fmax/20. : break
        #if dF < 0. : break
   return i 

def plot_conv(U,F,el,sig) :
   fig=plt.figure()
   ax = fig.add_subplot(111)
   y=np.linspace(np.min(F),np.max(F),2)
   x1=y/E
   l1 = plt.plot(U,F,color='k')
   l2 = plt.plot(x1,y,color='r',lw=1)
   l3 = plt.plot(el,sig,color='b')
   plt.xlim((0.,0.7))
   plt.ylim((0.,700.))
   plt.text(0.005,np.max(F)*.4,'$E=%0.f$ MPa'%(E))
   plt.text(0.03,Rp02,'$R_{p\_0.2}=%.1f MPa$'%(Rp02))
   plt.text(Au,Rm+20.,'$R_{m}=%0.1f MPa, A_u=%0.2f$'%(Rm,Au))
   plt.text(A,F[len(F)-1],'$A=%0.2f$'%(A),horizontalalignment='center',
       verticalalignment='top')
   plt.text(0.05,650.,'Ratio.',color='b')
   plt.text(0.3,400.,'Conv.',color='k')
   plt.xlabel('$\Delta l/l_0, \epsilon_v$')
   plt.ylabel('$F/S_0, \sigma_v (MPa)$')
   plt.savefig('%s_conv.png'%name_out,dpi=300)
   plt.show()
   return

def plot_Lkd(el,es) :
   fig=plt.figure()
   ax = fig.add_subplot(111)
   x=np.linspace(np.min(el),np.max(el),2)
   y=-0.5*x
   y2=(-1./(1+Lkd))*x
   l1 = plt.plot(el,es,color='k')
   l2 = plt.plot(x,y,color='r',lw=1)
   l3 = plt.plot(x,y2,color='b',lw=1, ls=':')
   plt.xlim((0.,np.max(el)))
   plt.ylim((np.min(es),0.))
   plt.text(np.max(el)/2.,np.min(es)/3,u'Plasticité isotrope',color='r')
   plt.text(np.max(el)/2.,np.min(es)/3,u'Plasticité isotrope',color='r')
   plt.text(np.max(el)/2.,4*np.min(es)/5,u'Coefficient de Lankford =  %0.2f'%Lkd,color='k',weight='bold', ha='center')
   plt.xlabel('$ln(1.+\Delta l/l_0)_p$')
   plt.ylabel('$ln(1.+\Delta \Phi/\Phi_0)_p$')
   plt.grid(color='k', marker='d')
   plt.savefig('%s_lkd.png'%name_out,dpi=300)
   plt.show()
   return

def plot_nu(el,et) :
   fig=plt.figure()
   ax = fig.add_subplot(111)
   x=np.linspace(np.min(el),0.05,2)
   y=-nu*x
   l1 = plt.plot(el,et,color='k')
   l2 = plt.plot(x,y,color='r',lw=1)
   plt.xlim((0.,0.10))
   plt.ylim((-0.04,0.))
#   plt.text(0.015/2.,-0.0015,u'Elasticité',color='r')
   plt.text(0.05,-0.01,u'$\\nu=$  %0.2f'%nu,color='k',weight='bold', ha='center')
   plt.xlabel('$\Delta l/l_0$')
   plt.ylabel('$\Delta \Phi/\Phi_0$')
   plt.grid(color='k', marker='d')
   plt.savefig('%s_nu.png'%name_out,dpi=300)
   plt.show()
   return

def plot_vitesse(T,U) :
   fig=plt.figure()
   ax = fig.add_subplot(111)
   x=np.linspace(np.min(T),2000.,2.)
   y=vitesse*x
   l1 = plt.plot(T,U,color='k')
   l2 = plt.plot(x,y,color='r',lw=1)
   plt.xlim((0.,2000.))
   plt.ylim((0.,0.5))
   plt.text(1000.,0.5,u'vitesse=  %0.6f'%vitesse,color='k',weight='bold', ha='center')
   plt.xlabel('Time (s)')
   plt.ylabel('$\Delta l/l_0$')
   plt.grid(color='k', marker='d')
   plt.savefig('%s_vitesse.png'%name_out,dpi=300)
   plt.show()
   return

def plot_U_F(U,F) :
   fig=plt.figure()
   ax = fig.add_subplot(111)
   l1,= plt.plot(U,F, 'o')
   plt.ylabel('$F/S_0 (N/mm^2)$')
   plt.xlabel('$\Delta l /l_0 (mm/mm)$')
   plt.grid(True)
   plt.show()
   return

def write_data(data,every):
   fout=name_out+".res"
   res=data[::every,[c_tps,c_ext,c_load,c_rad]]
   out=open(fout,'w')
   out.writelines("#time\tExt\tLoad\tRad\n")
   out.writelines("# \tDl/l0\tF/S0\tDPhi/Phi0\n")
   out.writelines("#s\tmm/mm\tMPa\tmm/mm\n")
   for i in range(len(res)): 
      line = "%g\t%g\t%g\t%g\n" % (res[i,0],res[i,1],res[i,2],res[i,3])
      out.writelines(line)
   out.close()
   return

def write_report():
   fout=name_out+".tex"
   tex_name_out=""
   for char in name_out :
      if char=="_" : char="\_"
      tex_name_out=tex_name_out+char
   print tex_name_out
   out=open(fout,'w')
   out.writelines(" \\documentclass[a4paper,10pt,french]{article} \n")
   out.writelines(" \\usepackage{graphicx} \n")
   out.writelines(" \\usepackage[latin1]{inputenc}\n")
   out.writelines(" \\usepackage[T1]{fontenc} \n")
   out.writelines(" \\usepackage{fancyhdr} \n")
   out.writelines(" \\usepackage{color} \n")
   out.writelines(" \\usepackage{geometry} \n")
   out.writelines(" \\usepackage{babel} \n")
   out.writelines(" \\usepackage{textcomp} \n")
   out.writelines(" \\usepackage{mathenv} \n")
   out.writelines(" \\usepackage{array} \n")
   out.writelines(" \\usepackage{multirow} \n")
   out.writelines(" \\usepackage{empheq} \n")
   out.writelines(" \\geometry{a4paper,tmargin=1.5cm,bmargin=1.5cm,lmargin=1.cm,rmargin=1.cm,headheight=0.5cm,headsep=0.5cm,footskip=1cm} \n")
   out.writelines(" \\columnsep=0.6cm \n")
   out.writelines(" \\fancypagestyle{plain}{ \n")
   out.writelines(" \\lfoot{Caracterisation de la loi de comportement de l'acier X65} \n")
   out.writelines(" \\rfoot{C.Soret} \n")
   out.writelines(" \\renewcommand{\headrulewidth}{0.4pt}} \n")
   out.writelines(" \\date\\today \n")
   out.writelines(" \\author{C.Soret} \n")
   out.writelines(" \\title{Compte-rendu d\'essai : Essai de traction simple\\\ Eprouvette : %s  }" % tex_name_out)
   out.writelines(" \\pagestyle{plain} \n")
   out.writelines(" \\begin{document} \n")
   out.writelines(" \\maketitle \n")
#   out.writelines(" \\thispagestyle{plain} \n")
   out.writelines(" \\subsection*{Formulaire}\n")
   out.writelines(" \\begin{minipage}[l]{0.5\linewidth} \n")
   out.writelines("  \n")
   out.writelines(" Loi de conservation de la mati\\`ere : $\\Delta V = 0 \\Rightarrow \\epsilon_l + \\epsilon_s + \\epsilon_t = 0 $ \\\ \n")
   out.writelines(" D\\'eformation axiale vraie : $\\epsilon_l=ln(1.+\\Delta l/l_0) $ \\\ \n")
   out.writelines(" D\\'eformation transversale vraie :  $\\epsilon_s=ln(1.+\\Delta \\Phi/\\Phi_0) $ \\\ \n")
   out.writelines(" Contrainte vraie : $\\sigma_v=F/S_0 \cdot (1+\\Delta l/l_0) $ \\\ \n")
   out.writelines(" Le coefficient de Lankford  est d\\'etermin\\'e par calcul de la pente de la courbe $\\epsilon_{l,p}$ \\textit{vs} $\\epsilon_{s,p}$, mesur\\'ee entre 1\\% et 2\\% de d\\'eformation (voir Fig. 2) \\\ \n")
   out.writelines(" \\end{minipage} \hfill \n")
   out.writelines(" \\begin{minipage}[r]{0.5\linewidth} \n")
   out.writelines(" \\begin{center} \n")
   out.writelines(" \\includegraphics[width = 0.7\\textwidth]{eprouvette_ST.png} \n")
   out.writelines(" \\end{center}\n")
   out.writelines(" \\end{minipage} \hfill \n")
   out.writelines(" \\subsection*{Courbes r\\'esultats} \n")
   out.writelines(" \\begin{figure}[h!] \n")
   out.writelines(" \\begin{minipage}[c]{0.5\linewidth} \n")
   out.writelines(" \\includegraphics[width = 1.\\textwidth]{"+name_out+"_conv.png} \n")
   out.writelines(" \\caption{Courbes Contraintes - D\\'eformations} \n")
   out.writelines(" \\end{minipage} \hfill \n")
   out.writelines(" \\begin{minipage}[c]{0.5\linewidth} \n")
   out.writelines(" \\includegraphics[width = 1.\\textwidth]{"+name_out+"_lkd.png} \n")
   out.writelines(" \\caption{Coefficient de Lankford} \n")
   out.writelines(" \\end{minipage} \hfill \n")
   out.writelines(" \\end{figure}  \n")
   out.writelines(" \\subsection*{R\\'esultats} \n")
   out.writelines(" \\begin{table}[h!] \n")
   out.writelines(" \\begin{tabular*}{1.\\textwidth}{@{\\extracolsep{\\fill}} lr|lr} \n")
   out.writelines(" \\multicolumn{2}{c|}{\\textbf{             Conditions exp\\'erimentales            }} & \\multicolumn{2}{c}{\\textbf{Propri\\'et\\'es de traction}} \\\ \hline  \n")
   out.writelines(" \\textbf{G\\'eom\\'etrie de l'\\'eprouvette} & & Module d'\\'elasticit\\'e, E & " + str(np.around(E,0)) + " MPa \\\ \n")
   out.writelines(" Longueur utile, $L_u$ & " + str(L0) + " mm & Coefficient de Poisson, $\\nu$ & " + str(np.around(nu,2)) + " \\\ \n")
   out.writelines(" Extensom\\`etre longi, $l_0$ & " + str(l0) + " mm & Limite d'\\'elasticit\\'e \\`a 0.2$\\%$, $R_{p0.2}$ & " + str(np.around(Rp02,0)) + " MPa \\\ \n")
   out.writelines(" Diam\\`etre initial, $\\Phi_0$ &" + str(phi0) + " mm & Limite d'\\'elasticit\\'e \\`a 0.5$\\%$, $R_{p0.5}$ & " + str(np.around(Rp05,0)) + " MPa \\\ \n")
   out.writelines(" & & R\\'esistance \\`a la traction, $R_m$ & " + str(np.around(Rm,0)) + " MPa \\\ \n")
   out.writelines(" \\textbf{Conditions exp\\'erimentales} & & Allongement uniforme, $A_u$ & " + str(np.around(Au,2)) + " $\\%$ \\\ \n")
   out.writelines(" Temp\\'erature de l'essai   & " + str(temperature) + " $^{\\circ}$ C    & Allongement \\`a rupture, A & " + str(np.around(Av,2)) + " $\\%$ \\\ \n")
   out.writelines(" Machine essai   & " + machine + " & & \\\ \n")
   out.writelines(" R\\'ef\\'erence extensom\\`etre longi   & " + extenso_longi + " & & \\\ \n")
   out.writelines(" R\\'ef\\'erence extensom\\`etre diam\\`etral   & " + extenso_rad + " & & \\\ \n")
   out.writelines(" Date de l'essai & " + date + " & & \\\ \n")
   out.writelines(" Vitesse de d\\'eformation axiale & " + str(np.around(vitesse,4)) + " $s^{-1}$" " & & \\\ \n")
   out.writelines(" \\end{tabular*} \n")
   out.writelines(" \\end{table} \n")
   out.writelines(" \\end{document} \n")
   out.close()
   os.system("pdflatex %s" % fout)
   os.system("rm -f *.tex *.aux *.log *_lkd.png *_conv.png")
   return

def main() :
   global Dict,finp, c_load, c_tps, c_ext, c_rad, search, name_out,imax,L0,l0,phi0,Rp02,Rp05,Rm,E,A,Au,Av,nu,temperature,vitesse,Lkd,extenso_longi, extenso_rad, machine, date,input_data

   Dict={}
   input_data = sys.argv[1]
# formatage du fichier .txt en fichier .prn 
   replace_virgule_point(input_data) 
   inp = open(sys.argv[1],'r')
   lines= inp.readlines()
   inp.close()

# construction d'un dictionnaire pour la lecture des arguments
   make_dict(lines)
   data=[]
# Valeurs par défaut 
   every=1
   search=1.
   sig1=0.
   sig2=400.
   nu=0.33
   temperature=20.
   machine = " "
   extenso_longi = " " 
   extenso_rad = " " 
   date = " " 

#on suppose que les données sont en kN pour la force et mm pour les déplacements
   kN_to_N=1000.
   for key, val in Dict.items():
   # print key, val
      if   key=="l0"  : l0=float(val[0][0])
      elif key=="L0"  : L0=float(val[0][0])
      elif key=="phi0" : phi0=float(val[0][0])
      elif key=="nu" : nu=float(val[0][0])
      elif key=="temperature" : temperature=float(val[0][0])
      elif key=="vitesse" : vitesse=float(val[0][0])
      elif key=="machine" : machine=str(val[0][0])
      elif key=="extenso_longi" : extenso_longi=str(val[0][0])
      elif key=="extenso_rad" : extenso_rad=str(val[0][0])
      elif key=="date" : date=str(val[0][0])
      elif key=="c_load"  : c_load=int(val[0][0])-1
      elif key=="c_ext"  : c_ext=int(val[0][0])-1
      elif key=="c_rad"  : c_rad=int(val[0][0])-1
      elif key=="c_ll"  : c_ll=int(val[0][0])-1
      elif key=="c_tps"  : c_tps=int(val[0][0])-1
      elif key=="every"  : every=int(val[0][0])
      elif key=="search"  : search=int(val[0][0])
      elif key=="out"  : name_out=(val[0][0]).split('.')[0]
      elif key=="apply"  : continue 
      elif key=="p_apply"  : continue 
      elif key=="in" :
         finp=val[0][0]
         data=LoadF(finp)
         for inp in val[1:] :
            finp=inp[0]
            datai=LoadF(finp)
            data=np.concatenate((data,datai))    
      else :
         print "Unknown argument : " + key +"\nvalid arguments are: l0, L0, phi0, nu, temperature, vitesse, c_ll, c_rad, c_load, c_ext, c_tps, every, search, in, out, apply, p_apply"
         sys.exit(0)

# calcul de la section de l'éprouvette
   try :
      S0=math.pi*phi0*phi0/4.
   except :
      print "Argument phi0 is not available, please put in the file : " + sys.argv[1]
      sys.exit(0)

   if 'out' in Dict: pass
   else : name_out=finp.split('.')[0]

   print phi0, l0, L0
   data[:,c_ext]=data[:,c_ext]/l0
   data[:,c_rad]=data[:,c_rad]/phi0
   data[:,c_ll]=data[:,c_ll]/L0
   data[:,c_load]=data[:,c_load]/S0*kN_to_N
   data=apply(data,"before")
# extraction du maximum
   F=data[:,c_load]
   imax=find_maxs(F)[0]
   #data=data[:filtre_load_drop(F,imax),:]
   T=data[:,c_tps]
   U=data[:,c_ext]
   V=data[:,c_ll]
   R=data[:,c_rad]
   F=data[:,c_load]
# calcul des propriétés de traction (module, Rp02, Rp05, Rm, Au, A, Av) Av: Allongement à Rupture Vérin
   E,eps0=getslope(U[:imax],F[:imax],sig1,sig2)
   Er,epsr0=getslope(R[:imax],F[:imax],sig1,sig2)
   Ev,dv0=getslope(V[:imax],F[:imax],sig1,sig2)
#   vitesse=1./getslope(U[:imax],T[:imax],1000.,1500.)[0]
#   plot_vitesse(T,U)
   U=U-eps0
   R=R-epsr0
   nu=getslope(U[:imax],-R[:imax],0.,0.0005)[0]
#   plot_nu(U,R)
#   print "poisson = ", nu
   V=V-dv0
   Rp02=np.interp(0.002,U-F/E,F)   
   Rp05=np.interp(0.005,U-F/E,F)   
   Au=U[imax]
   Rm=F[imax]
   A=U[-1]
   Av=V[-1]-F[-1]/Ev
#  plot_U_F(V,F)
#  plot_U_F(V-F/Ev,F)
# calcul des déformations vraies et du lankford
   el=np.log(1.+U[:imax]);es=np.log(1.+R[:imax])
   sig=F[:imax]*(1.+U[:imax])
   el_p=np.log(1.+U[:imax])-sig/E;
   es_p=np.log(1.+R[:imax])+sig/E*nu;
   et_p=-(el_p+es_p);
   Lkd=getslope(-es_p,-et_p,0.02,0.05)[0] 
#Tracer de la courbe de traction 
   plot_conv(U,F,el,sig)
#Tracer de la courbe de Lankford 
   plot_Lkd(el_p,es_p)
#extraction des cycles avec echantillonage
   data=apply(data,"after")
#ecriture des données globales traitée
   write_data(data[:imax,:],every)   
   write_report()
main()
