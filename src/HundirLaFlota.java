import java.util.Scanner;
	
public class HundirLaFlota {
	
	//Inicialización de la matriz
	static void inicializarMatriz(int[][] array)
	{
		for(int i=0; i<array.length; i++)
		{
			for(int j=0; j<array[i].length; j++)
			{
				array[i][j]=0;
			}
		}
	}
	
	//Inicializamos los arrays de los barcos
	static void inicializarBarco(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			array[i]=0;
		}
	}
	
	//Bienvenida para cuando es el primer inicio del juego
	static void bienvenida()
	{
		String autor="David Pérez";
		System.out.println("---------HUNDIR LA FLOTA------ ver.3.0------");
		System.out.println("---Versión creada por: " + autor + "---\n");
	}
	
	//Funcion para mostrar el menú por pantalla y elegir una opción por parte del usuario
	static int mostrarMenu(Scanner t)
	{
		int opcion;
	
		do {
			System.out.println("\n-----MENÚ PRINCIPAL-----\n");
			System.out.println("=== Elija una opción ===");
			System.out.println("1. Iniciar un juego nuevo."); //comenzamos un nuevo juego desde 0 en modo oculto
			System.out.println("2. Iniciar un juego nuevo en modo ayuda (en proceso, aun no funciona)."); //nuevo juego mostrando los barcos en tablero
			System.out.println("0. Salir"); //Salir del programa
			System.out.print("\nOpción: ");
			opcion=t.nextInt();
			t.nextLine();
			if(opcion<0 || opcion>2)
				System.out.println("\nError!, la opción no está dentro del rango [0-2]");
		}while(opcion<0 || opcion>2);
		return opcion;
	}
	
	//Funcion para mostrar el submenú para lanzar misiles
	static int mostrarSubMenu(Scanner t)
	{
		int opcion=0;
		do {
			System.out.println("\n-----MENÚ JUEGO-----\n");
			System.out.println("=== Elija la opción que desea ===");
			System.out.println("1. Lanzar misil."); //lanzamos un misil al tablero
			System.out.println("0. Terminar juego y volver al menú princial."); //terminamos la partida y volvemos al menú principal
			System.out.print("\nOpcion: ");
			opcion=t.nextInt();
			t.nextLine();
			if(opcion<0 || opcion>1)
				System.out.println("\nError!, la opción no está dentro del rango [0-1]");
		}while(opcion<0 || opcion>1);
		System.out.println();
		return opcion;
	}
	
	//Función para imprimir el campo del juego.
	static void dibujarCuadricula(int[][] array)
	{
		char limvertical='¦', limhorizontal='-';
		
		System.out.println();
		System.out.println("    ======= TABLERO DE JUEGO =======\n");
		
		//Imprimimos las coordenadas de la coordenada X
		System.out.print("\\X  ");
		for(int h=0; h<=20; h++)
		{
			if(indiceImpares(h))
				System.out.print((h/2+1) + "   ");
		}
		System.out.println();
		
		//Imprimimos la cuadrícula de juego.
		for(int j=0; j<=20; j++)
		{
			//Imprimimos las coordeandas de la coordeanda Y
			if(j==0)
				System.out.print("Y ");
			else
				if(indiceImpares(j) && j<=19)
					if(j==19)
						System.out.print(j/2+1 + "");
					else
						System.out.print(j/2+1 + " ");
				else
					System.out.print("  ");
			
			//Creamos la cuadrícula dele tablero y lo rellenamos con los datos.
			for(int i=0; i<=20; i++)
			{
				if(i==0 || i==20)
				{
					System.out.print(limvertical + " ");
				}
				else
				{
					if(j==0 || j==20)
					{
						System.out.print(limhorizontal + " ");
					}
					else
					{
						if(!indiceImpares(j))
						{
							System.out.print(limhorizontal + " ");
						}
						else
						{
							if(!indiceImpares(i))
								System.out.print(limvertical + " ");
							else
									//Mostramos el los detalles el juego, con sus carácteres correspondientes según
									//sea barco, agua, impacto o sin lanzar en esa posición.
									System.out.print(tipoElemento(array[i/2][j/2]) + " ");
						}
					}
				}
			}
			System.out.println();
		}
	}
	
	//Función para imprimir el campo del juego en modo ayuda (mostrando los barcos en el tablero)
		static void dibujarCuadriculaAyuda(int[][] array)
		{
			char limvertical='¦', limhorizontal='-';
			
			System.out.println();
			System.out.println("    ======= TABLERO DE JUEGO =======\n");
			
			//Imprimimos las coordenadas de la coordenada X
			System.out.print("\\X  ");
			for(int h=0; h<=20; h++)
			{
				if(indiceImpares(h))
					System.out.print((h/2+1) + "   ");
			}
			System.out.println();
			
			//Imprimimos la cuadrícula de juego.
			for(int j=0; j<=20; j++)
			{
				//Imprimimos las coordeandas de la coordeanda Y
				if(j==0)
					System.out.print("Y ");
				else
					if(indiceImpares(j) && j<=19)
						if(j==19)
							System.out.print(j/2+1 + "");
						else
							System.out.print(j/2+1 + " ");
					else
						System.out.print("  ");
				
				//Creamos la cuadrícula dele tablero y lo rellenamos con los datos.
				for(int i=0; i<=20; i++)
				{
					if(i==0 || i==20)
					{
						System.out.print(limvertical + " ");
					}
					else
					{
						if(j==0 || j==20)
						{
							System.out.print(limhorizontal + " ");
						}
						else
						{
							if(!indiceImpares(j))
							{
								System.out.print(limhorizontal + " ");
							}
							else
							{
								if(!indiceImpares(i))
									System.out.print(limvertical + " ");
								else
										//Mostramos el los detalles el juego, con sus carácteres correspondientes según
										//sea barco, agua, impacto o sin lanzar en esa posición.
										System.out.print((array[i/2][j/2]) + " ");
							}
						}
					}
				}
				System.out.println();
			}
		}

	//Nos calcula si el indice es un número impar para la representación gráfica
	static boolean indiceImpares(int a)
	{
		boolean impar=false;
		
		if(a%2!=0)
			impar=true;
		
		return impar;
	}
	//Funcion para crear barcos aleatorios de tamaño 1 en el área de juego
	static void crearBarcosT1(int[][] array, int barco)
	{
		int posx=-1, posy=-1;
		
		do {
			posx=(int)(Math.random()*10);
			posy=(int)(Math.random()*10);
		}while(array[posx][posy]!=0);
		
		array[posx][posy]=barco;
	}
	
	//Funcion para crear barcos aleatorios de tamaño 2 en el área de juego
	static void crearBarcosT2(int[][] array, int barco)
	{
		int posx1=-1, posy1=-1, direccion=-1, posx2=-1, posy2=-1;
		boolean generado=false;
		
		do {
			//generamos puntos aleatorios que ya no estén ocupados en la matriz
			do {
				posx1=(int)(Math.random()*10);
				posy1=(int)(Math.random()*10);
			}while(array[posx1][posy1]!=0);
			
			direccion=(int)(Math.random()*2);
			
			//Según posicionemos el barco en vertical u horizontal
			if(direccion==1)
			{
				if(posx1+1<10 && array[posx1+1][posy1]==0)
				{
					posx2=posx1+1;
					posy2=posy1;
					generado=true;
				}
			}
			else
				if(posy1+1<10 && array[posx1][posy1+1]==0)
				{
					posy2=posy1+1;
					posx2=posx1;
					generado=true;
				}
		}while(!generado);

		array[posx1][posy1]=barco;
		array[posx2][posy2]=barco;
	}
	
	//Funcion para crear barcos aleatorios de tamaño 3 en el área de juego
	static void crearBarcosT3(int[][] array, int barco)
	{
		int posx1=-1, posy1=-1, direccion=-1, posx2=-1, posy2=-1, posx3=-1, posy3=-1;
		boolean generado=false;
		
		do {
			//generamos puntos aleatorios que ya no estén ocupados en la matriz
			do {
				posx1=(int)(Math.random()*10);
				posy1=(int)(Math.random()*10);
			}while(array[posx1][posy1]!=0);
			
			direccion=(int)(Math.random()*2);
			
			//Según posicionemos el barco en vertical u horizontal
			if(direccion==1)
			{
				if(posx1+2<10 && array[posx1+1][posy1]==0 && array[posx1+2][posy1]==0)
				{
					posx2=posx1+1;
					posx3=posx1+2;
					posy2=posy1;
					posy3=posy1;
					generado=true;
				}
			}
			else
				if(posy1+2<10 && array[posx1][posy1+1]==0 && array[posx1][posy1+2]==0)
				{
					posy2=posy1+1;
					posy3=posy1+2;
					posx2=posx1;
					posx3=posx1;
					generado=true;
				}
		}while(!generado);
	
		array[posx1][posy1]=barco;
		array[posx2][posy2]=barco;
		array[posx3][posy3]=barco;
	}
	
	//Funcion para crear barcos aleatorios de tamaño 4 en el área de juego
	static void crearBarcosT4(int[][] array, int barco)
	{
		int posx1=-1, posy1=-1, direccion=-1, posx2=-1, posy2=-1, posx3=-1, posy3=-1, posx4=-1, posy4=-1;
		boolean generado=false;
		
		do {
			//generamos puntos aleatorios que ya no estén ocupados en la matriz
			do {
				posx1=(int)(Math.random()*10);
				posy1=(int)(Math.random()*10);
			}while(array[posx1][posy1]!=0);
			
			direccion=(int)(Math.random()*2);
			
			//Según posicionemos el barco en vertical u horizontal
			if(direccion==1)
			{
				if(posx1+3<10 && array[posx1+1][posy1]==0 && array[posx1+2][posy1]==0 && array[posx1+3][posy1]==0)
				{
					posx2=posx1+1;
					posx3=posx1+2;
					posx4=posx1+3;
					posy2=posy1;
					posy3=posy1;
					posy4=posy1;
					generado=true;
				}
			}
			else
				if(posy1+3<10 && array[posx1][posy1+1]==0 && array[posx1][posy1+2]==0 && array[posx1][posy1+3]==0)
				{
					posy2=posy1+1;
					posy3=posy1+2;
					posy4=posy1+3;
					posx2=posx1;
					posx3=posx1;
					posx4=posx1;
					generado=true;
				}
		}while(!generado);
	
		array[posx1][posy1]=barco;
		array[posx2][posy2]=barco;
		array[posx3][posy3]=barco;
		array[posx4][posy4]=barco;
	}
	
	//Funcion para crear barcos aleatorios de tamaño 5 en el área de juego
	static void crearBarcosT5(int[][] array, int barco)
	{
		int posx1=-1, posy1=-1, direccion=-1, posx2=-1, posy2=-1, posx3=-1, posy3=-1, posx4=-1, posy4=-1, posx5=-1, posy5=-1;
		boolean generado=false;
		
		do {
			//generamos puntos aleatorios que ya no estén ocupados en la matriz
			do {
				posx1=(int)(Math.random()*10);
				posy1=(int)(Math.random()*10);
			}while(array[posx1][posy1]!=0);
			
			direccion=(int)(Math.random()*2);
			
			//Según posicionemos el barco en vertical u horizontal
			if(direccion==1)
			{
				if(posx1+4<10 && array[posx1+1][posy1]==0 && array[posx1+2][posy1]==0 && array[posx1+3][posy1]==0 && array[posx1+4][posy1]==0)
				{
					posx2=posx1+1;
					posx3=posx1+2;
					posx4=posx1+3;
					posx5=posx1+4;
					posy2=posy1;
					posy3=posy1;
					posy4=posy1;
					posy5=posy1;
					generado=true;
				}
			}
			else
				if(posy1+4<10 && array[posx1][posy1+1]==0 && array[posx1][posy1+2]==0 && array[posx1][posy1+3]==0 && array[posx1][posy1+4]==0)
				{
					posy2=posy1+1;
					posy3=posy1+2;
					posy4=posy1+3;
					posy5=posy1+4;
					posx2=posx1;
					posx3=posx1;
					posx4=posx1;
					posx5=posx1;
					generado=true;
				}
		}while(!generado);
	
		array[posx1][posy1]=barco;
		array[posx2][posy2]=barco;
		array[posx3][posy3]=barco;
		array[posx4][posy4]=barco;
		array[posx5][posy5]=barco;
	}
	
	//Funcion para lanzar misiles usando la coordeanda X.
	static int lanzarMisilX(Scanner t)
	{
		int coordenadaX=-1;

		do {
			System.out.print("Introduzca la coordenada X [1-10]: ");
			coordenadaX=t.nextInt();
			t.nextLine();
			if(coordenadaX<1 || coordenadaX>10)
				System.out.println("Error!, coordeanda X ha de estar dentro del ranto [1-10].");
		}while(coordenadaX<1 || coordenadaX>10);
		coordenadaX--;
		
		return coordenadaX;
	}
	
	//Funcion para lanzar misiles usando la coordanda Y.
	static int lanzarMisilY(Scanner t)
	{
		int coordenadaY=-1;
		
		do {
			System.out.print("Introduzca la coordenada Y [1-10]: ");
			coordenadaY=t.nextInt();
			t.nextLine();
			if(coordenadaY<1 || coordenadaY>10)
				System.out.println("Error!, coordeanda Y ha de estar dentro del ranto [1-10].");
		}while(coordenadaY<1 || coordenadaY>10);
		coordenadaY--;
		
		return coordenadaY;
	}
	
	//Funcion para comprobar el impacto cuando se lanza un misil y aplicarlo al área de juego
	static boolean comprobarImpacto(int[][] array, int X, int Y)
	{
		boolean impacto=false;
		
		//Modificado en (--(V2)--)
		if(array[X][Y]==0 || array[X][Y]==-1)
			array[X][Y]=-1;
		/*
		if(array[X][Y]==0 || array[X][Y]==-1)
			array[X][Y]=-1;
		*/
		else
		{
			//Modificado en (--(V2)--)
			//array[X][Y]=3;
			if(array[X][Y]!=9) //Si no se había alcanzado antes lo marcamos como impacto, si se había alcanzado, como agua.
				impacto=true;
		}
		return impacto;
	}
	
	//Añadido en (--(V2)--)
	//Funcion para comprobar que barco se ha alcanzado
	static int barcoAlcanzado(int[][] array, int X, int Y)
	{
		int numBarco=-1;
		numBarco=array[X][Y];
		array[X][Y]=9; //Una vez alcanzado lo marcamos en la matriz como tocado.
		return numBarco;
	}
	
	
	//Añadido en (--(V2)--)
	//Marcamos en la matriz de cada barco el impacto y comprobamos si se hunde o no.
	static boolean marcarImpacto(int[] barco)
	{
		int toques=0;
		boolean hundido=false;
		
		for(int i=0; i<barco.length; i++)
		{
			if(barco[i]!=1)
			{
				barco[i]=1;
				i=barco.length;
			}
			toques++;
		}
		if(toques==barco.length)
			hundido=true;
		
		return hundido;
	}
	
	//Añadido en (--(V2)--)
	//Marcamos en las matrices de cada barco un toque y devolvemos si se hunde o no.
	static int tocarBarco(int balcanzado, int[] barco1, int[] barco2, int[] barco3, int[] barco4, int[] barco5, int[] barco6)
	{
		int hundido=0;
		
		System.out.println("\n--- ¡ENHORABUENA!, has alcanzado el barco " + balcanzado + "!. ---");
		switch(balcanzado)
		{
			case 1:
				if(marcarImpacto(barco1))
					hundido=1;
				break;
			case 2:
				if(marcarImpacto(barco2))
					hundido=2;
				break;
			case 3:
				if(marcarImpacto(barco3))
					hundido=3;
				break;
			case 4:
				if(marcarImpacto(barco4))
					hundido=4;
				break;
			case 5:
				if(marcarImpacto(barco5))
					hundido=5;
				break;
			case 6:
				if(marcarImpacto(barco6))
					hundido=6;
				break;
		}
		if(hundido!=0)
			System.out.println("\n--- ¡ENHORABUENA!, has HUNDIDO el barco " + hundido + "!. ---");
		return hundido;
	}
	
	//Modificado en (--(V2)--)
	//Sustituimos el valor interno del tablero por el símbolo correspondiente para su representación
	static char tipoElemento(int elemento)
	{
		char simbolo=' ';
		
		switch(elemento)
		{
			//Añadido en (--(V2)--)
			case 0: //cuando aun no se ha lanzado y es agua
				simbolo='?';
				break;
			case -1: //cuando se ha lanzado y es agua
				simbolo='°';
				break;
			case 9: //cuando se ha lanzado y ha impactado es un barco
				simbolo='¤';
				break;
			default: //cuando aun no se ha lanzado y es barco
				simbolo='?';
				break;
				
				//Modificado en (--(V2)--)
				/*  
			case 0: //cuando aun no se ha lanzado y es agua
				simbolo='~';
				break;
			case -1: //cuando se ha lanzado y es agua
				simbolo='°';
				break;
			case 9: //cuando se ha lanzado y ha impactado en un barco
				simbolo='¤';
				break;
			default: //cuando aun no se ha lanzado y es barco
				simbolo='~';
				break;
				*/
		}
		return simbolo;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Generamos nuestras variables
		Scanner teclado = new Scanner(System.in);
		
		//Creamos la matriz del campo de juego
		int[][] area = new int[10][10];
		
		//Añadido en (--(V2)--)
		//Creamos los barcos con sus respectivos nombres (Para otra versión crear una método que pida al usuario los tamaños)
		int[] barco1=new int[1];
		int[] barco2=new int[1];
		int[] barco3=new int[2];
		int[] barco4=new int[3];
		int[] barco5=new int[4];
		int[] barco6=new int[5];
		
		int opcion=-1, opcion2=-1;

		//En el primer inicio mostramos la bienvenida
		bienvenida();
		do {
			int cX=-1, cY=-1, barcos=0, balcanzado=0, numbarco=0;
			boolean alcanzado=false;
			
			//Añadido en (--(V2)--)
			//Generamos el número de barcos de cada tipo que queramos.
			int barcosD1=2;
			int barcosD2=1;
			int barcosD3=1;
			int barcosD4=1;
			int barcosD5=1;
			
			//Añadido en (--(V2)--)
			//Para cada barco nos marcamos si están hundidos o no
			boolean b1=false;
			boolean b2=false;
			boolean b3=false;
			boolean b4=false;
			boolean b5=false;
			boolean b6=false;
			
			//Mostramos el menú para que el usuario elija una opción:
			opcion=mostrarMenu(teclado);
			if(opcion==1)
			{
				System.out.println("\nEspere unos instantes, creando el área de juego...\n");
				//Inicializamos la matriz
				inicializarMatriz(area);
				
				//Añadido en (--(V2)--)
				//Inicializamos los barcos 
				inicializarBarco(barco1);
				inicializarBarco(barco2);
				inicializarBarco(barco3);
				inicializarBarco(barco4);
				inicializarBarco(barco5);
				
				//Creamos los barcos aleatorios;
				for(int i=0; i<barcosD1; i++)
				{
					numbarco++;
					crearBarcosT1(area, numbarco);
				}
				
				//Añadido en (--(V2)--)
				for(int i=0; i<barcosD2; i++) 
				{
					numbarco++;
					crearBarcosT2(area, numbarco);

				}
				
				for(int i=0; i<barcosD3; i++)
				{
					numbarco++;
					crearBarcosT3(area, numbarco);
				}
				
				for(int i=0; i<barcosD4; i++)
				{
					numbarco++;
					crearBarcosT4(area, numbarco);
				}
				
				for(int i=0; i<barcosD5; i++)
				{
					numbarco++;
					crearBarcosT5(area, numbarco);
				}
				
				//Imprimimos la cuadrícula de juego.
				dibujarCuadricula(area);
				
				do {
					//Mostramos el menú para lanzar misiles
					opcion2=mostrarSubMenu(teclado);
					if(opcion2==1)
					{
						cX=lanzarMisilX(teclado); //Lanzamos los misiles en la coordanda X
						cY=lanzarMisilY(teclado); //Lanzamos los misiles en la coordadna Y
						alcanzado=comprobarImpacto(area, cX, cY); //Comprobamos si ha impactado en agua o en barco y lo registramos.
						
						//Añadido en (--(V2)--)
						//Mostramos al usuario si ha alcanzado un barco o ha dado en agua.
						if(alcanzado)
						{
							balcanzado=barcoAlcanzado(area, cX, cY); //Comprobamos que barco ha alcanzado.

							//Imprimimos la cuadrícula de juego.
							dibujarCuadricula(area);
							
							barcos=tocarBarco(balcanzado, barco1, barco2, barco3, barco4, barco5, barco6); //Marcamos en la raíz del barco tocado.
							
							switch(barcos) //Marcamos el barco que ha sido hundido.
							{
								case 1:
									b1=true;
									break;
								case 2:
									b2=true;
									break;
								case 3:
									b3=true;
									break;
								case 4:
									b4=true;
									break;
								case 5:
									b5=true;
									break;
								case 6:
									b6=true;
									break;
								default:
									break;
							}
						}
						else
						{
							//Imprimimos la cuadrícula de juego.
							dibujarCuadricula(area);
							System.out.println("\n--- ¡HAS TOCADO AGUA!, vuelve a intentarlo. ---");
						}
					}
					else {
						if(opcion2<0 || opcion2>1)
							System.out.println("Error!, la opcion está fuera del rango [0-1].");
					}
				}while((opcion2==1 || opcion2<0 || opcion2>1) && !(b1 && b2 && b3 && b4 && b5 && b6));
				
				if(b1 && b2 && b3 && b4 & b5 && b6)
				{
					System.out.println("\n\n====!Enhorabuena, has derrotado todos los barcos!. !Vuelve a jugar!====");
					System.out.println("-------------------------------------------------------------------\n");
				}
			}
			else
			{
				System.out.println("\nEspere unos instantes, creando el área de juego...\n");
				
				//Inicializamos la matriz
				inicializarMatriz(area);
				
				//Añadido en (--(V2)--)
				//Inicializamos los barcos 
				inicializarBarco(barco1);
				inicializarBarco(barco2);
				inicializarBarco(barco3);
				inicializarBarco(barco4);
				inicializarBarco(barco5);
				
				//Creamos los barcos aleatorios;
				for(int i=0; i<barcosD1; i++)
				{
					numbarco++;
					crearBarcosT1(area, numbarco);
				}
				
				//Añadido en (--(V2)--)
				for(int i=0; i<barcosD2; i++) 
				{
					numbarco++;
					crearBarcosT2(area, numbarco);

				}
				
				for(int i=0; i<barcosD3; i++)
				{
					numbarco++;
					crearBarcosT3(area, numbarco);
				}
				
				for(int i=0; i<barcosD4; i++)
				{
					numbarco++;
					crearBarcosT4(area, numbarco);
				}
				
				for(int i=0; i<barcosD5; i++)
				{
					numbarco++;
					crearBarcosT5(area, numbarco);
				}
				
				//Imprimimos la cuadrícula de juego.
				dibujarCuadriculaAyuda(area);
				
				do {
					//Mostramos el menú para lanzar misiles
					opcion2=mostrarSubMenu(teclado);
					if(opcion2==1)
					{
						cX=lanzarMisilX(teclado); //Lanzamos los misiles en la coordanda X
						cY=lanzarMisilY(teclado); //Lanzamos los misiles en la coordadna Y
						alcanzado=comprobarImpacto(area, cX, cY); //Comprobamos si ha impactado en agua o en barco y lo registramos.
						
						//Añadido en (--(V2)--)
						//Mostramos al usuario si ha alcanzado un barco o ha dado en agua.
						if(alcanzado)
						{
							balcanzado=barcoAlcanzado(area, cX, cY); //Comprobamos que barco ha alcanzado.

							//Imprimimos la cuadrícula de juego.
							dibujarCuadriculaAyuda(area);
							
							barcos=tocarBarco(balcanzado, barco1, barco2, barco3, barco4, barco5, barco6); //Marcamos en la raíz del barco tocado.
							
							switch(barcos) //Marcamos el barco que ha sido hundido.
							{
								case 1:
									b1=true;
									break;
								case 2:
									b2=true;
									break;
								case 3:
									b3=true;
									break;
								case 4:
									b4=true;
									break;
								case 5:
									b5=true;
									break;
								case 6:
									b6=true;
									break;
								default:
									break;
							}
						}
						else
						{
							//Imprimimos la cuadrícula de juego.
							dibujarCuadriculaAyuda(area);
							System.out.println("\n--- ¡HAS TOCADO AGUA!, vuelve a intentarlo. ---");
						}
					}
					else {
						if(opcion2<0 || opcion2>1)
							System.out.println("Error!, la opcion está fuera del rango [0-1].");
					}
					
				}while((opcion2==1 || opcion2<0 || opcion2>1) && !(b1 && b2 && b3 && b4 && b5 && b6));
				
				if(b1 && b2 && b3 && b4 & b5 && b6)
				{
					System.out.println("\n\n====!Enhorabuena, has derrotado todos los barcos!. !Vuelve a jugar!====");
					System.out.println("-------------------------------------------------------------------\n");
				}
			}
		}while(opcion!=0);
		
		System.out.println();
		System.out.println("-----FIN DEL JUEGO HUNDIR LA FLOTA----");
		
	}
}


