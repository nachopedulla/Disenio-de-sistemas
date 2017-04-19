import java.util.ArrayList;
import java.util.List;

public class Balance {
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	int periodo;
	
	public Balance(int _periodo) {
			periodo = _periodo;
	}
	public Cuenta cuenta() {
		return cuenta();
	}
	public int periodo(){
		return periodo;
	}
	public void agregarCuenta(Cuenta unaCuenta){
		cuentas.add(unaCuenta);
	}
	public void mostrarContenido() {
		System.out.printf("periodo: %d\n", periodo);
		int posicion = 0;
		while (posicion < cuentas.size()){
			Cuenta cuenta = cuentas.get(posicion);
			System.out.printf("Cuenta: %s\n", cuenta.nombre());
			System.out.printf("Valor: %d\n", cuenta.valor());
			posicion ++;
		}
	}
}
