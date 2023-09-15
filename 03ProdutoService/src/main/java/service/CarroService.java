package service;

import java.io.IOException;

import dao.CarroDAO;
import model.Carro;
import spark.Request;
import spark.Response;


public class CarroService {

	private CarroDAO carroDAO;

	public CarroService() {
		try {
			carroDAO = new CarroDAO("carro.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String modelo = request.queryParams("modelo");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int cavalos = Integer.parseInt(request.queryParams("cavalos"));

		int id = carroDAO.getMaxId() + 1;

		Carro carro = new Carro(id, modelo, preco, cavalos);

		carroDAO.add(carro);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Carro carro = (Carro) carroDAO.get(id);
		
		if (carro != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<carro>\n" + 
            		"\t<id>" + carro.getId() + "</id>\n" +
            		"\t<modelo>" + carro.getModelo() + "</modelo>\n" +
            		"\t<preco>" + carro.getPreco() + "</preco>\n" +
            		"\t<cavalos>" + carro.getCavalos() + "</cavalos>\n" +
            		"</carro>\n";
        } else {
            response.status(404); // 404 Not found
            return "Carro " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Carro carro = (Carro) carroDAO.get(id);

        if (carro != null) {
        	carro.setModelo(request.queryParams("modelo"));
        	carro.setPreco(Float.parseFloat(request.queryParams("preco")));
        	carro.setCavalos(Integer.parseInt(request.queryParams("cavalos")));

        	carroDAO.update(carro);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Carro não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Carro carro = (Carro) carroDAO.get(id);

        if (carro != null) {

            carroDAO.remove(carro);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Carro não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<carros type=\"array\">");
		for (Carro carro : carroDAO.getAll()) {
			returnValue.append("\n<carro>\n" + 
            		"\t<id>" + carro.getId() + "</id>\n" +
            		"\t<modelo>" + carro.getModelo() + "</modelo>\n" +
            		"\t<preco>" + carro.getPreco() + "</preco>\n" +
            		"\t<cavalos>" + carro.getCavalos() + "</cavalos>\n" +
            		"</carro>\n");
		}
		returnValue.append("</carros>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}