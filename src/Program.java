import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class Program {

	public static void main(String argv[]) throws FileNotFoundException{
		// lista de empresas del programa
		List<Empresa> empresas = new ArrayList<Empresa>();
		File archivo = new File("C:\\archivo.txt");
		cargarCuentas(archivo, empresas);
		archivo.delete();
		empresas.forEach(empresa -> empresa.mostrarBalances());
	}
	

	public static Empresa buscarEmpresa(String nombre, List<Empresa> empresas){
		// Filtra la lista para ver si hay alguna empresa con el mismo nombre que la que se esta buscando
		// Si encuentra lo devuelve 
		// Si no lo encuentra devuelve null
		Optional<Empresa> a = empresas.stream()
 				.filter(empresa -> empresa.nombre().equals(nombre))
				.findFirst();
		return a.isPresent() ? a.get() : null;
	}	


	private static void cargarCuentas(File archivo, List<Empresa> empresas) throws FileNotFoundException {
		// El sacenner lee linea por linea hasta el delimitador
		// Le puse la coma pero puede ser cualquier cosa
		Scanner scanner = new Scanner(archivo);
		scanner.useDelimiter(",");
		String linea;
		String palabra;
		
		while (scanner.hasNext()){
			linea = scanner.next();
			int periodo;
			String nombreCuenta;
			int valor;
			// El StringTokenizer separa las palabras de la linea leida.
			// La primer palabra es la empresa, se la busca con la funcion. Si no exites la crea.
			StringTokenizer separador = new StringTokenizer(linea);
			palabra = separador.nextToken();
		
			Empresa unaEmpresa = buscarEmpresa(palabra,empresas);

			// leo el periodo al cual pertenece el balance
			periodo = Integer.parseInt(separador.nextToken());
			Balance unBalance = new Balance(periodo);
			// leo la primer cuenta con su valor y la agrego a la lista de cuentas del balance
			nombreCuenta = separador.nextToken();
			valor = Integer.parseInt(separador.nextToken());
			unBalance.agregarCuenta(new Cuenta(nombreCuenta,valor));
			
			// si hay mas cuentas las agrego a la lista
			while (separador.hasMoreTokens()){
				nombreCuenta = separador.nextToken();
				valor = Integer.parseInt(separador.nextToken());
				unBalance.agregarCuenta(new Cuenta(nombreCuenta,valor));

			}
			// agrego el balance a la empresa
			unaEmpresa.agregarElemento(unBalance);
			
			
		}
	}

//	private static Balance buscarBalance(int periodo, Empresa unaEmpresa) {
//		Optional<Balance> balance = unaEmpresa.balances()
//							.stream()
//							.filter(b -> b.periodo().equals(periodo))
//							.findFirst();
//	return balance.isPresent() ? balance.get() : null;
//	}
}