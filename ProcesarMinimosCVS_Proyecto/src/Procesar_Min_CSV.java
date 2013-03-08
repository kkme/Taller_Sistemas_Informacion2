import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Procesar_Min_CSV {

	public static void main(String[] args) throws IOException {

		String instancia, mejorMK1, mejorTD1, promMK1, promTD1, sigmaMK1, sigmaTD1, mejorMK2, mejorTD2, promMK2, promTD2, sigmaMK2, sigmaTD2;

		String lineaArchivo = "";
		String salida = "";
		BufferedReader bf = new BufferedReader(
				new FileReader(
						"C:\\Users\\Marcos Bellucci SA\\Desktop\\tablas\\compromiso\\valores.csv"));
		String linea;
		int i=0;
		FileWriter fw = new FileWriter("C:\\Users\\Marcos Bellucci SA\\Desktop\\tablas\\compromiso\\SalidaValores.csv",false);
		BufferedWriter bw = new BufferedWriter(fw);
		while ((linea = bf.readLine()) != null) {

			try {
				String[] lineaSplit = linea.split("&");
				instancia = lineaSplit[0];
				System.out.println(instancia);
				mejorMK1 = lineaSplit[1];
				mejorTD1 = lineaSplit[2];
				promMK1 = lineaSplit[3];
				promTD1 = lineaSplit[4];
				sigmaMK1 = lineaSplit[5];
				sigmaTD1 = lineaSplit[6];

				mejorMK2 = lineaSplit[7];
				mejorTD2 = lineaSplit[8];
				promMK2 = lineaSplit[9];
				promTD2 = lineaSplit[10];
				sigmaMK2 = lineaSplit[11];
				sigmaTD2 = lineaSplit[12].replace("\\\\", "");

				if (i>0&&Double.valueOf(mejorMK1) < Double.valueOf(mejorMK2)) {
					mejorMK1 = "\\textbf{" + mejorMK1 + "}";
				} else {
					mejorMK2 = "\\textbf{" + mejorMK2 + "}";
				}

				if (i>0&&Double.valueOf(mejorTD1) < Double.valueOf(mejorTD2)) {
					mejorTD1 = "\\textbf{" + mejorTD1 + "}";
				} else {
					mejorTD2 = "\\textbf{" + mejorTD2 + "}";
				}

				if (i>0&&Double.valueOf(promMK1) < Double.valueOf(promMK2)) {
					promMK1 = "\\textbf{" + promMK1 + "}";
				} else {
					promMK2 = "\\textbf{" + promMK2 + "}";
				}

				if (i>0&&Double.valueOf(promTD1) < Double.valueOf(promTD2)) {
					promTD1 = "\\textbf{" + promTD1 + "}";
				} else {
					promTD2 = "\\textbf{" + promTD2 + "}";
				}

				if (i>0&&Double.valueOf(sigmaMK1) < Double.valueOf(sigmaMK2)) {
					sigmaMK1 = "\\textbf{" + sigmaMK1 + "}";
				} else {
					sigmaMK2 = "\\textbf{" + sigmaMK2 + "}";
				}

				if (i>0&&Double.valueOf(sigmaTD1) < Double.valueOf(sigmaTD2)) {
					sigmaTD1 = "\\textbf{" + sigmaTD1 + "}";
				} else {
					sigmaTD2 = "\\textbf{" + sigmaTD2 + "}";
				}

				salida = instancia + "&" + mejorMK1 + "&" + mejorTD1 + "&"
						+ promMK1 + "&" + promTD1 + "&" + sigmaMK1 + "&"
						+ sigmaTD1 + "&" +

						"&" + mejorMK2 + "&" + mejorTD2 + "&" + promMK2 + "&"
						+ promTD2 + "&" + sigmaMK2 + "&" + sigmaTD2 + "\\\\\n";
				bw.write(salida);
			} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
					System.out.println("error linea "+linea);
			} catch ( java.lang.NumberFormatException ex){
				System.out.println("error linea "+i+"   "+linea);
				
			}
			i++;
		}
		bw.flush();
		fw.close();
		
	}

}
