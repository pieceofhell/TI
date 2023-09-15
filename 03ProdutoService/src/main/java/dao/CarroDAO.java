package dao;

import model.Carro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class CarroDAO {
	private List<Carro> carros;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public CarroDAO(String filename) throws IOException {
		file = new File(filename);
		carros = new ArrayList<Carro>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Carro carro) {
		try {
			carros.add(carro);
			this.maxId = (carro.getId() > this.maxId) ? carro.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o carro '" + carro.getModelo() + "' no disco!");
		}
	}

	public Carro get(int id) {
		for (Carro carro : carros) {
			if (id == carro.getId()) {
				return carro;
			}
		}
		return null;
	}

	public void update(Carro p) {
		int index = carros.indexOf(p);
		if (index != -1) {
			carros.set(index, p);
			this.saveToFile();
		}
	}

	public void remove(Carro p) {
		int index = carros.indexOf(p);
		if (index != -1) {
			carros.remove(index);
			this.saveToFile();
		}
	}

	public List<Carro> getAll() {
		return carros;
	}

	private List<Carro> readFromFile() {
		carros.clear();
		Carro carro = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				carro = (Carro) inputFile.readObject();
				carros.add(carro);
				maxId = (carro.getId() > maxId) ? carro.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar carro no disco!");
			e.printStackTrace();
		}
		return carros;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Carro carro : carros) {
				outputFile.writeObject(carro);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar carro no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}
}