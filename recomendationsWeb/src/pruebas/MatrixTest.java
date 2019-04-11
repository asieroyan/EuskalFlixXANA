package pruebas;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Vector;

import org.junit.Test;

import packEuskoFlix.Matrix;

public class MatrixTest {

	@Test
	public void testMatrix() {
		Matrix matrix1 = null;
		assertNull(matrix1);
		matrix1 = new Matrix();
		assertNotNull(matrix1);
	}

	@Test
	public void testAddData() {
		Matrix matrix1 = new Matrix();
		matrix1.addData(2,3 , 3.5);
		HashMap<Integer, Double> aux= new HashMap<Integer, Double>();
		aux.put(3, 3.5);
		assertEquals(matrix1.getValue(2, 3), aux.get(3));
	}

	@Test
	public void testContainsFirstKey() {
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,3 , 3.5);
		assertTrue(matrix1.containsFirstKey(3));
		assertFalse(matrix1.containsFirstKey(5));
	}

	@Test
	public void testContainsKeys() {
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,3 , 3.5);
		assertTrue(matrix1.containsKeys(3, 3));
		assertFalse(matrix1.containsKeys(3, 4));
		assertFalse(matrix1.containsKeys(4, 3));
	}

	@Test
	public void testGetFirstKeyList() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA IMPRIMIR PRIMERAS CLAVES");
		System.out.println("--------------------------------------------");
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,5 , 3.5);
		matrix1.addData(5,1 , 4.0);
		matrix1.addData(7,2 , 3.0);
		matrix1.addData(1,3 , 3.5);
		packEuskoFlix.VectorInteger vector1 = matrix1.getFirstKeyList();
		vector1.printVector();
	}

	@Test
	public void testGetSecondKeyList() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA IMPRIMIR SEGUNDAS CLAVES");
		System.out.println("--------------------------------------------");
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,5 , 3.5);
		matrix1.addData(5,1 , 4.0);
		matrix1.addData(7,2 , 3.0);
		matrix1.addData(1,3 , 3.5);
		packEuskoFlix.VectorInteger vector1 = matrix1.getFirstKeyList();
		vector1.printVector();
	}

	@Test
	public void testGetValue() {
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,3 , 3.5);
		assertEquals(matrix1.getValue(3, 3), (Double)3.5);
	}

	@Test
	public void testGetSecondKeySortedByValues() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA IMPRIMIR SEGUNDAS CLAVES ORDENADAS");
		System.out.println("--------------------------------------------");
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,5 , 3.5);
		matrix1.addData(5,1 , 4.0);
		matrix1.addData(7,2 , 3.0);
		matrix1.addData(1,3 , 3.5);
		packEuskoFlix.VectorInteger vector1 = matrix1.getFirstKeyList();
		vector1.printVector();
	}

	@Test
	public void testGetMatrixWithSecondKeySortedByValues() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA IMPRIMIR MATRIZ CON SEGUNDAS CLAVES ORDENADAS");
		System.out.println("--------------------------------------------");
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,5 , 3.5);
		matrix1.addData(5,1 , 4.0);
		matrix1.addData(7,2 , 3.0);
		matrix1.addData(1,3 , 3.5);
		matrix1.addData(3,3 , 3.5);
		matrix1.addData(3,1 , 4.0);
		matrix1.addData(3,2 , 3.0);
		matrix1.addData(1,3 , 3.5);
		matrix1.getMatrixWithSecondKeySortedByValues(3, 4).print(3);
	}

	@Test
	public void testGetMaxValueKey() {
		Matrix matrix1 = new Matrix();
		matrix1.addData(3,5 , 3.5);
		matrix1.addData(5,1 , 4.0);
		matrix1.addData(7,2 , 3.0);
		matrix1.addData(1,3 , 3.5);
	}

}
