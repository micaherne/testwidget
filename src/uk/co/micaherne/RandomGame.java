package uk.co.micaherne;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RandomGame
 */
@WebServlet("/service/randomgame")
public class RandomGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> games = new ArrayList<String>();

    /**
     * Default constructor. 
     * @throws IOException 
     */
    public RandomGame()  {
    	
		
    }
    
    

	@Override
	public void init() throws ServletException {
		super.init();
		FileReader fr = null;
		try {
			fr = new FileReader(this.getServletContext().getRealPath("/WEB-INF/Capablanca.pgn"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		String currentGame = "";
		int blankLineCount = 0;
		try {
			while ((line = br.readLine()) != null) {
				if ("".equals(line.trim())) {
					currentGame += "\n";
					if (blankLineCount++ == 1) {
						games.add(currentGame);
						currentGame = "";
						blankLineCount = 0;
					}
				} else {
					currentGame += line + "\n";
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		Random random = new Random();
		int gameNumber = random.nextInt(games.size());
		response.setContentType("text/plain");
		writer.write(games.get(gameNumber));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
