package agenda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import agenda.model.DAO;
import agenda.model.JavaBeans;
import agenda.model.exceptions.DataObjectAcessException;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	// Data acess object created
	private DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			adicionarContato(request, response);

		} else if (action.equals("/select")) {
			listarContato(request, response);

		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
		out.close();

	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criando objeto que receberá os dados JavaBeans
		ArrayList<JavaBeans> list = dao.mostrarContatos();

		// Teste exemplo
		/*
		 * for (int i = 0; i < list.size(); i++) {
		 * System.out.println(list.get(i).getIdcontato());
		 * System.out.println(list.get(i).getNome());
		 * System.out.println(list.get(i).getFone());
		 * System.out.println(list.get(i).getEmail()); }
		 */

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", list);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		// encaminha o objeto lista ao documento agenda.jsp
		rd.forward(request, response);

	}

	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// inserir metodo insertContato
		dao.insertContato(contato);
		// redireciona para agenda.jsp
		response.sendRedirect("main");

	}

	// Editar contato

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebendo o id do contato que será editado
		contato.setIdcontato(request.getParameter("idcontato"));

		// executar selecionarContato
		dao.selecionarContato(contato);

		request.setAttribute("idcontato", contato.getIdcontato());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// encaminhar ao editarcontato.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editarcontato.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setIdcontato(request.getParameter("idcontato"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		dao.updateContato(contato);

		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setIdcontato(request.getParameter("idcontato"));
		dao.deletarContato(contato);

		response.sendRedirect("main");

	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document documento = new Document();
		try {
			// precisa ser chamado para resetar a resposta e não gerar exceção.
			response.reset();
			// tipo de conteudo
			response.setContentType("apllication/pdf");
			// nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			// criando documento pdf
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o PDF para gerar conteudo
			documento.open();
			documento.add(new Paragraph("Lista de contatos"));
			documento.add(new Paragraph(" "));
			// criar uma tabela e o numero 3 indica que a tabela terá 3 colunas
			PdfPTable table = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);

			// Pupular a tabela com os contatos Javabeans
			ArrayList<JavaBeans> list = dao.mostrarContatos();
			for (int i = 0; i < list.size(); i++) {
				table.addCell(list.get(i).getNome());
				table.addCell(list.get(i).getFone());
				table.addCell(list.get(i).getEmail());
			}
			documento.add(table);
			documento.close();

		} catch (Exception e) {
			throw new DataObjectAcessException("Error trying to generate relatory: " + e.getMessage());

		}

	}

}

