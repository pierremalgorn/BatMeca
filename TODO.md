## To-do list

### Nettoyage

- Améliorer le layout des pages (position des éléments, alignements, marges...), c'est du grand n'importe quoi pour le moment. Mieux utiliser Bootstrap pour gérer des alignements.
- Nettoyer et améliorer le JavaScript qui est mis dans tous les sens dans les pages (notamment la page de détails d'un test).
- Nettoyer les fonctions permettant d'effectuer des traitements sur les courbes (CurveWebservice).
si possible, découper chaque fonction en différentes URL (/traitement/smooth, /traitement/factor...).
- Changer toutes les URL pour faire plus REST-full : /element/action/{id} (prendre exemple sur le contrôleur de l'administration).

### Robustesse
- Ajouter en JavaScript une vérification de la saisie dans les champs des formulaires.
- Ajouter des try/catch dans les contrôleurs pour récupérer les exceptions et afficher des messages d'erreurs dans les pages web en conséquence.
- Utiliser une libraire de logging (style *slf4j*) pour écrire dans un fichier de logs.
- Utiliser des pages webs spécialement pour l'affichage des erreurs (pas les pages par défaut de Tomcat).
- Vérifier que toutes les dépendances (test, attributs...) sont supprimées lorsque l'on supprime un "material".
    - Si MySQL donne des erreurs d'élément non trouvé après la suppression d'un matériau, il faut regarder dans la BDD et supprimer les liens restant vers ce matériau.
- Traduire le script shell en Java pour pouvoir s'en passer.
- Améliorer la façon dont les tests sont stockés
    - en ce moment ils sont tous dans un même dossier, un dossier par test. Cela peut poser des problèmes par rapport au nombre maximum de fichiers autorisés dans un seul dossier.
        - Stocker les tests groupés par utilisateurs par exemple...


### Améliorations

#### PDF
- Utiliser une librairie gérant le ***flex*** au lieu de la librairie de génération de PDF utilisée actuellement.
- Utiliser des templates en *flex* pour la génération de PDF, éventuellement paramétrables par l'utilisateur.
- Générer un PDF avec les courbes choisies par l'utilisateur.

#### Courbes
- Générer les courbes côté serveur et non côté client (ou bien faire en sorte que les courbes dans le PDF ressemblent à celles côté client).
- Utiliser le fichier de paramètres importé pour traiter les courbes.
- A corriger : dans la page de détails d’un test, il faut rafraichir la page si l’on veut voir un graphe fraichement généré, et cliquer deux fois dessus pour qu’il s’affiche complètement.

#### Autre
- Comprendre à quoi sert la page ConfigGenerator (dans le code source).
- Améliorer la façon dont fonctionnent l'historique de traitement et les résultats d’opérations sur les données de test.
- Les onglets sélectionnés ne sont pas en surbrillance au chargement de la page.
- Ajouter une hierarchie entre les utilisateurs standards (ex: un chercheur a un stagiaire, il doit pouvoir accéder au travail de ce dernier, mais pas l'inverse)


### Failles de sécurité

#### Urgent
- Dans la page de détail d'un test, pour pouvoir récupérer les courbes on donne au client un chemin absolu vers le fichier de la courbe.
    - Donner uniquement l'id de la courbe en plus de l'id du test.

#### Pour la mise en production
- Ne pas afficher les stacktraces des erreurs aux utilisateurs, utiliser seulement les fichiers de logs sur le serveur. *(Il faut leur en montrer le moins possible pour éviter de leur donner des informations qu'ils pourraient exploiter)*
- Connexions sécurisées
    - Pour la connexion serveur/client
    - Pour la connexion serveur/BDD
