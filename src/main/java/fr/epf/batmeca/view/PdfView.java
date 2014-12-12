package fr.epf.batmeca.view;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.TestAttribute;

/**
 * Generate a PDF file from a {@link Test}.
 */
public class PdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		Test test = (Test) model.get("test");

		doc.add(new Paragraph(test.getName()));
		doc.add(new Paragraph(test.getMaterial().getName()));

		Set<TestAttribute> sta = test.getTestAttributs();
		if (sta.size() > 0) {
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100.0f);
			table.setWidths(new float[] { 1.0f });
			table.setSpacingBefore(10);

			// define font for table header row
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(BaseColor.WHITE);

			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setPadding(5);

			// write table header
			cell.setPhrase(new Phrase("Test attribute type", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Value", font));
			table.addCell(cell);

			for (TestAttribute ta : sta) {
				table.addCell(ta.getTypeTestAttr().getName());
				table.addCell(ta.getValue());
			}

			doc.add(table);
		}
	}
}
