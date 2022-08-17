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

import agenda.model.DAO;
import agenda.model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Data acess object created
	private DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Acessado");

		String action = request.getServletPath();

		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			novoContato(request, response);

		} else if (action.equals("/select")) {
			listarContato(request, response);

		} else {
			response.sendRedirect("index.html");
		}

	}

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

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
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

	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebendo o id do contato que será editado
		String idcontato = request.getParameter("idcontato");
		System.out.println(idcontato);

		// setar variavel JavaBeans
		contato.setIdcontato(idcontato);

		// executar selecionarContato
		dao.selecionarContato(contato);

		// teste
		/*
		 * System.out.println(contato.getIdcontato());
		 * System.out.println(contato.getNome()); System.out.println(contato.getFone());
		 * System.out.println(contato.getEmail());
		 */
	}

}
