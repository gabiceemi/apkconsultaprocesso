package apprpv.grupo3rpv.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import apprpv.grupo3rpv.Model.Domain.Andamento;
import apprpv.grupo3rpv.R;

/**
 * Classe responsável pela formatação padrão do PDF
 */
@SuppressWarnings("JavaDoc")
public class GeradorPDF {
    private static final String TAG = "GeradorPDF";
    private final ArrayList<Andamento> andamentos;

    public GeradorPDF(ArrayList<Andamento> andamentos) throws FileNotFoundException, DocumentException  {
        this.andamentos = andamentos;
    }

    /**
     * Método responsável por montar o PDF de acordo com a formatação padrão
     * @param numCP numero de controle do processo
     * @param numProc numero do processo
     * @param data data do processo
     * @param tipo tipo do processo
     * @param departamento departamento do processo
     * @param instituicao instituição do processo(sempre é a prefeitura de Alegrete)
     * @param requerente nome do requerente do processo
     * @param observacao observação do processo
     * @param nome nome do titular do processo
     * @param hora hora da criação do processo
     * @param atendente atendente que criou o processo
     * @param context contexto do estado atual do aplicativo
     * @return pdfFile
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public File montaPDF (Integer numCP, String numProc, String data, String tipo, String departamento, String instituicao, String requerente, String observacao, String nome, String hora, String atendente, Context context) throws FileNotFoundException, DocumentException{
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Extrato Andamentos");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "NOVO DIRETÓRIO PARA PDF CRIADO");
        }

        File pdfFile = new File(docsFolder.getAbsolutePath(), "Extrato de Andamentos.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document(PageSize.A4);
        PdfPTable table = new PdfPTable(5);
        PdfWriter.getInstance(document, output);
        document.setMargins(20, 20, 30, 30);
        document.setMarginMirroring(false);
        document.open();

        PdfPTable header = new PdfPTable(2);
        header.setWidths(new int[]{2, 12});
        header.setTotalWidth(527);
        header.setLockedWidth(true);
        header.getDefaultCell().setFixedHeight(50);
        header.getDefaultCell().setBorder(Rectangle.BOTTOM);
        header.getDefaultCell().setBorderColor(BaseColor.BLACK);

        //Inserir Imagem
        Drawable d = ContextCompat.getDrawable(context, R.drawable.brasao_alegrete_pdf);
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapData = stream.toByteArray();
        Image image = null;
        try {
            image = Image.getInstance(bitmapData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        header.addCell(image);
        //Inserir Texto
        PdfPCell text = new PdfPCell();
        text.setPaddingBottom(15);
        text.setPaddingLeft(30);
        text.setBorder(Rectangle.BOTTOM);
        text.setBorderColor(BaseColor.BLACK);
        text.addElement(new Phrase("PREFEITURA DE ALEGRETE RS", new Font(Font.FontFamily.HELVETICA, 18)));
        text.addElement(new Phrase("Rua Major João Cezimbra Jacques, 200", new Font(Font.FontFamily.HELVETICA, 14)));
        text.addElement(new Phrase("ALEGRETE - RS", new Font(Font.FontFamily.HELVETICA, 14)));
        text.addElement(new Phrase("55 39611729", new Font(Font.FontFamily.HELVETICA, 14)));
        text.addElement(new Phrase("comprasalegrete@bol.com.br", new Font(Font.FontFamily.HELVETICA, 14)));
        text.addElement(new Phrase("http://www.alegrete.rs.gov.br", new Font(Font.FontFamily.HELVETICA, 14)));
        text.setVerticalAlignment(Element.ALIGN_CENTER);
        header.addCell(text);

        PdfPTable infoProcesso = new PdfPTable(2);
        infoProcesso.setTotalWidth(PageSize.A4.getWidth() - 72);
        infoProcesso.setLockedWidth(true);
        infoProcesso.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPTable leftTable = new PdfPTable(2);
        leftTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell leftCellLabel = new PdfPCell(Phrase.getInstance("\nNúmero de Controle do Processo: " +
                "\nNúmero do Processo: " +
                "\nData: " +
                "\nTipo: " +
                "\nRequerente: " +
                "\nObservação: "));
        leftCellLabel.setBorder(Rectangle.NO_BORDER);
        leftCellLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell leftCell = new PdfPCell(Phrase.getInstance("\n\n" + numCP +
                "\n" + numProc +
                "\n" + data +
                "\n" + tipo +
                "\n" + requerente +
                "\n" + observacao +"\n\n" ));
        leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        leftCell.setBorder(Rectangle.NO_BORDER);
        leftTable.addCell(leftCellLabel);
        leftTable.addCell(leftCell);

        PdfPTable rightTable = new PdfPTable(2);
        rightTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell rightCellLabel = new PdfPCell(Phrase.getInstance(("\nTitular do Processo: " +
                "\nHora: " +
                "\nAtendente: " +
                "\nInstituição: ")));
        rightCellLabel.setBorder(Rectangle.NO_BORDER);
        rightCellLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);

        PdfPCell rightCell = new PdfPCell(Phrase.getInstance("\n" + nome + "\n" + hora +
                "\n"+ atendente +
                "\nPREFEITURA DE ALEGRETE RS\n\n" ));
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        rightTable.addCell(rightCellLabel);
        rightTable.addCell(rightCell);

        document.add(header);

        infoProcesso.addCell(leftTable);
        infoProcesso.addCell(rightTable);

        PdfPCell space = new PdfPCell(Phrase.getInstance("\n\n\n"));
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        rightTable.addCell(rightCellLabel);
        rightTable.addCell(rightCell);

        infoProcesso.addCell(rightTable);

        document.add(infoProcesso);

        PdfPTable tableElements = new PdfPTable(new float[]{2, 3, 4, 3});
        tableElements.setTotalWidth(PageSize.A4.getWidth() - 72);
        tableElements.setLockedWidth(true);
        tableElements.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        tableElements.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableElements.addCell("Data");
        tableElements.addCell("Departamento");
        tableElements.addCell("Ocorrência");
        tableElements.addCell("Despacho");
        tableElements.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        tableElements.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        tableElements.getDefaultCell().setBorderWidth(0);
        tableElements.getDefaultCell().setPaddingBottom((float) 0.5);

        for (Andamento andamento: andamentos) {
            tableElements.addCell(new Phrase(andamento.getData()));
            tableElements.addCell(new Phrase(andamento.getDepto()));
            tableElements.addCell(new Phrase(andamento.getOcorrencia()));
            tableElements.addCell(new Phrase(andamento.getDespacho()));
        }

        document.add(tableElements);
        document.close();
        return pdfFile;
    }

}
