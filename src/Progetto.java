import progetto2019.Helper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Progetto {
	public static boolean controllo (int righe, int colonne, int [][] mat)
	{
		//controllo che non ci siano numeri negativi, se ci sono ritorna falso perche non puo essere una matrice di mosse
		for (int i=0; i<righe; i++)
			{ 
				for (int j=0; j<colonne; j++)
						if (mat[i][j]<0) return false;
			}
		//controllo che gli altri numeri non siano doppi tra i primi in ordine, se ci sono altri numeri non in successione gestisco nei vari casi
		int indice=1;		//valore da cercare nella matrice
		int conta=1; 		//mi serve per sapere quante volte compare l'indice nella matrice
		while (conta!=0)	//il while continua solo fino a quando i numeri sono in successione e unici nella matrice
		{
			conta=0;
			for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (mat[i][j]==indice)
					conta++;
				}
			}
			if (conta!=1&&conta!=0)	//se l'indice compare un numero di volte diverso da 1 e 0, non e' una matrice di mosse perche indica che l'indice compere piu volte
				return false;
			else indice++;
		}
		return true;	//se nei due casi non ho ottenuto che non e' una matrice di mosse mi permette di andare nei diversi casi
	}
	
	public static boolean re (int righe, int colonne, int [][] mat)
	{
		/*
		 * per il re trovo prima l'1 nella matrice salvando la posizione
		 * cerco poi le posizioni successive nelle 8 caselle adiacenti alla posizione precedentemente salvata
		 * salvo la nuova posizione e ripeto fino a quando non trovo piu numeri in sequenza
		 * sono sicura di non avere numeri doppi tra quelli cercati perche lo controllo nella funzione "controllo"
		 * ogni volta che trovo la nuova posizione nella matrice parallela la casella corrispondente diventa true
		 * controllo infine la matrice controllo 
		 * se ho posizioni in cui ho un valore false nella parallela e un numero diverso da 0 nella mat non ho una matrice di mosse, altrimenti si
		 */
		boolean [][] controllo; //creo una matrice simmetrica alla matrice da controllare in cui metto valori booleani che cambiano valore quando trovo la mossa
		controllo = new boolean [righe][colonne];
		int posr=0, posc=0;
		boolean fine=true;
		boolean inizio=false;
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
					controllo[i][j]=false;
			}
		int indice=1;
		for (int i=0; i<righe; i++) 	//cerco l'1, se non lo trova non inizia il resto del codice
		{ 
			for (int j=0; j<colonne; j++)
					{						
							if (mat[i][j]==indice)
									{
										controllo [i][j]=true;
										posr=i;
										posc=j;
										fine=false;
										inizio=true;
										break;
									}
					}
		}
		if (inizio==false) return false;
		while (fine==false)
				{
					indice++;
					if (posr>0&&posc>0&&mat[posr-1][posc-1]==indice) {
														controllo [posr-1][posc-1]=true; 		
														posr=posr-1;
														posc=posc-1;
													}
					else if (posr>0&&mat[posr-1][posc]==indice) {
														controllo [posr-1][posc]=true;
														posr=posr-1;
														}
						else if (posr>0&&posc<(colonne-1)&&mat[posr-1][posc+1]==indice) {
														controllo [posr-1][posc+1]=true;
														posr=posr-1;
														posc=posc+1;
																}
							else if (posc>0&&mat[posr][posc-1]==indice) {
														controllo [posr][posc-1]=true;
														posc=posc-1;
																}
								else if (posc<(colonne-1)&&mat[posr][posc+1]==indice) {
														controllo [posr][posc+1]=true;
														posc=posc+1;
																	}
									else if (posr<(righe-1)&&posc>0&&mat[posr+1][posc-1]==indice) {
														controllo [posr+1][posc-1]=true;
														posr=posr+1;
														posc=posc-1;
																		}
										else if (posr<(righe-1)&&mat[posr+1][posc]==indice) {
														controllo [posr+1][posc]=true;
														posr=posr+1;
																			}
											else if (posr<(righe-1)&&posc<(colonne-1)&&mat[posr+1][posc+1]==indice) {
														controllo [posr+1][posc+1]=true;
														posr=posr+1;
														posc=posc+1;
																				}
												else fine=true;
				}
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (controllo[i][j]==false && mat[i][j]!=0) return false;
				}
			}
	return true;
	}
	
	public static boolean donna (int righe, int colonne, int [][] mat)
	{
		/*
		 * per la regina cerco il valore 1 nella matrice e salvo la posizione, cambiando la stessa posizione a true nella matrice controllo
		 * cerco poi i valori successivi andando a incrementare e salvo in un altro parametro la posizione
		 * per essere una matrice di mosse della regina o la differenza in valore assoluto delle righe tra la posizione precedente e quella che sto esaminando e' nulla (stanno sulla stessa riga)
		 * o la differenza in valore assoluto delle colonne tra la posizione precedente e quella che sto esaminando e' nulla (stanno sulla stessa colonna)
		 * o la differenza in valore assoluto delle righe tra la posizione precedente e quella che sto esaminando e' uguale alla differenza in valore assoluto delle colonne tra la posizione precedente e la posizione che sto esaminando (si muove in obliquo)
		 * infine controllo che nella matrice controllo i valori false siano associati nella mat a valori 0
		 */
		boolean [][] controllo; //creo una matrice simmetrica alla matrice da controllare in cui metto valori booleani che cambiano valore quando trovo la mossa
		controllo = new boolean [righe][colonne];
		int posr=0, posc=0;
		boolean fine=true;
		boolean inizio=false;
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
					controllo[i][j]=false;
			}
		int indice=1;
		for (int i=0; i<righe; i++) 
		{ 
			for (int j=0; j<colonne; j++)
					{						
							if (mat[i][j]==indice)
									{
										controllo [i][j]=true;
										posr=i;
										posc=j;
										fine=false;
										inizio=true;	//se non trova l'1 non inizia il codice dopo
									}
					}
		}
		if (inizio==false) return false;
		int findr, findc, drighe, dcolonne;
		boolean trovato;
		while (fine==false)
				{
				trovato=false;
				indice++;
				for (int i=0; i<righe; i++)
				{
					for (int j=0; j<colonne; j++)
					{
						if (mat[i][j]==indice)
						{
							findr=i;
							findc=j;
							drighe=Math.abs(posr-findr);	//differenza in valore assoluto delle righe tra la posizione precedente e quella che sto esaminando
							dcolonne=Math.abs(posc-findc);	//differenza in valore assoluto delle colonne tra la posizione precedente e quella che sto esaminando
							if (drighe==0||dcolonne==0||drighe==dcolonne)
							{
								posr=findr;
								posc=findc;
								trovato=true;
								controllo[posr][posc]=true;
							}
						}
					}
				}	
				if (trovato==false) fine=true;
				}
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (controllo[i][j]==false && mat[i][j]!=0) return false;
				}
			}
	return true;
	}
	
	public static boolean alfiere (int righe, int colonne, int [][] mat)
	{
		/*
		 * per l'alfiere trovo l'1 nella mat e salvo la posizione
		 * incremento l'indice e salvo momentaneamente la posizione
		 * se la differenza in valore assoluto delle righe tra la posizione precedente e quella che esamino e' uguale alla differenza in valore assoluto delle colonne tra la posizione precedente e quella che sto esaminando
		 * allora puo essere la mossa dell'alfiere
		 * ogni volta che trovo dei valori compatibili con una matrice di mosse cambio a true la posizione corrispondente nella matrice controllo
		 * controllo che i valori false nella matrice controllo siano associati a 0 nella mat
		 */
		boolean [][] controllo; //creo una matrice simmetrica alla matrice da controllare in cui metto valori booleani che cambiano valore quando trovo la mossa
		controllo = new boolean [righe][colonne];
		int posr=0, posc=0;
		boolean fine=true;
		boolean inizio=false;
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
					controllo[i][j]=false;
			}
		int indice=1;
		for (int i=0; i<righe; i++)
		{ 
			for (int j=0; j<colonne; j++)
					{						
							if (mat[i][j]==indice)
									{
										controllo [i][j]=true;
										posr=i;
										posc=j;
										fine=false;
										inizio=true;	//se non trova l'1 non inizia il codice dopo
										break;
									}
					}
		}
		if (inizio==false) return false;
		int findr, findc;
		boolean trovato;
		while (fine==false)
		{
			indice++;
			trovato=false;
			for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (mat[i][j]==indice)
					{
						findr=i;
						findc=j;
						int drighe=Math.abs(posr-findr);	//differenza in valore assoluto delle righe tra la posizione precedente e quella che sto esaminando
						int dcolonne=Math.abs(posc-findc);	//differenza in valore assoluto delle colonne tra la posizione precedente e quella che sto esaminando
						if (drighe==dcolonne)
						{
							posr=findr;
							posc=findc;
							trovato=true;
							controllo[posr][posc]=true;
						}
					}
				}
			}
			if (trovato==false) fine=true;
		}
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (controllo[i][j]==false && mat[i][j]!=0) return false;
				}
			}
	return true;
	}
	
	public static boolean torre (int righe, int colonne, int [][] mat)
	{
		/*
		 * ricerco l'1 nella matrice
		 * incremento il valore da cercare, cerco il valore nella matrice mat e controllo se sta sulla stessa riga o sulla stessa colonna della posizione precedente
		 * ogni volta che trovo un valore compatibile con una matrice di mosse il valore nella posizione corrispondente nella matrice controllo diventa true
		 * controllo che nella matrice controllo i valori false siano associati nella mat a 0, altrimenti non e' una matrice di mosse
		 */
		boolean [][] controllo; //creo una matrice simmetrica alla matrice da controllare in cui metto valori booleani che cambiano valore quando trovo la mossa
		controllo = new boolean [righe][colonne];
		int posr=0, posc=0;
		boolean fine=true;
		boolean inizio=false;
		for (int i=0; i<righe; i++)	//inizializzo la matrice controllo a falso perchè non ho ancora controllato nessuna casella
			{
				for (int j=0; j<colonne; j++)
					controllo[i][j]=false;
			}
		int indice=1;
		for (int i=0; i<righe; i++) 
		{ 
			for (int j=0; j<colonne; j++)
					{						
							if (mat[i][j]==indice)
									{
										controllo [i][j]=true;
										posr=i;
										posc=j;
										fine=false;
										inizio=true;	//se non trova l'1 non inizia il codice dopo
										break;
									}
					}
		}
		if (inizio==false) return false;	//non ha trovato l'1 quindi non è una matrice di mosse 
		int findr, findc;
		boolean find;
		while (fine==false)
		{
			indice++;
			find=false;
			for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (mat[i][j]==indice)
					{
						findr=i;
						findc=j;
						if (findr==posr||findc==posc)
						{
							posr=findr;
							posc=findc;
							controllo[posr][posc]=true;
							find=true;
						}
					}
				}	
			}
			if (find==false) fine=true;
		}
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (controllo[i][j]==false && mat[i][j]!=0) return false;
				}
			}
	return true;
	}
	
	public static boolean cavallo (int righe, int colonne, int [][] mat)
	{
		/*
		 * cerco l'1 nella mat e salvo la posizione e incremento il valore da cercare
		 * cerco l'indice richiesto e controllo che sia in una delle 8 possibili posizioni raggiungibili dal cavallo dalla posizione precedente
		 * ad ogni posizione compatibile cambio la corrispondente posizione nella matrice controllo a valore true
		 * controllo che nella matrice controllo tutti i valori false siano associati a valori 0 nella mat  
		 */
		boolean [][] controllo; //creo una matrice simmetrica alla matrice da controllare in cui metto valori booleani che cambiano valore quando trovo la mossa
		controllo = new boolean [righe][colonne];
		int posr=0, posc=0;
		boolean fine=true;
		boolean inizio=false;
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
					controllo[i][j]=false;
			}
		int indice=1;
		for (int i=0; i<righe; i++) 
		{ 
			for (int j=0; j<colonne; j++)
					{						
							if (mat[i][j]==indice)
									{
										controllo [i][j]=true;
										posr=i;
										posc=j;
										fine=false;
										inizio=true;	//se non trova l'1 non inizia il codice dopo
										break;
									}
					}
		}
		if (inizio==false) return false;
		int findc, findr;
		boolean trovato;
		while (fine==false)
		{
			indice++;
			trovato=false;
			for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (mat[i][j]==indice)
					{
						findr=i;
						findc=j;
						if (findr==(posr-2)&&findc==(posc-1))
						{
							posr=posr-2;
							posc=posc-1;
							controllo[posr][posc]=true;
							trovato=true;
						}
						else if (findr==(posr-2)&&findc==(posc+1))
							{
								posr=posr-2;
								posc=posc+1;
								controllo[posr][posc]=true;
								trovato=true;
							}
							else if (findr==(posr+2)&&findc==(posc-1))
								{
									posr=posr+2;
									posc=posc-1;
									controllo[posr][posc]=true;
									trovato=true;
								}
								else if (findr==(posr+2)&&findc==(posc+1))
									{
										posr=posr+2;
										posc=posc+1;
										controllo[posr][posc]=true;
										trovato=true;
									}
									else if (findr==(posr+1)&&findc==(posc-2))
										{
											posr=posr+1;
											posc=posc-2;
											controllo[posr][posc]=true;
											trovato=true;
										}
										else if (findr==(posr+1)&&findc==(posc+2))
											{
												posr=posr+1;
												posc=posc+2;
												controllo[posr][posc]=true;
												trovato=true;
											}
											else if (findr==(posr-1)&&findc==(posc-2))
												{
													posr=posr-1;
													posc=posc-2;
													controllo[posr][posc]=true;
													trovato=true;
												}
												else if (findr==(posr-1)&&findc==(posc+2))
													{
														posr=posr-1;
														posc=posc+2;
														controllo[posr][posc]=true;
														trovato=true;
													}
					}
				}	
			}
			if (trovato==false) fine=true;
		}
		for (int i=0; i<righe; i++)
			{
				for (int j=0; j<colonne; j++)
				{
					if (controllo[i][j]==false && mat[i][j]!=0) return false;
				}
			}
	return true;
	}
	
	public static boolean testChessMoves(String fileName) {
		// Qui potete inserire il vostro codice
		String pezzo;
		int n,m;
		int [][] matrice;
		
		try {		//leggo nel file il nome del pezzo, la dimensione della matrice e la matrice
			Scanner f=new Scanner (new File (fileName));
			pezzo=f.next();
			n=f.nextInt();
			m=f.nextInt();
			matrice = new int [n][m];
			for (int i=0; i<n; i++)
								{
									for (int j=0; j<m; j++)
														{
															matrice[i][j]=f.nextInt();
														}
								}
			f.close();
		} catch (IOException e)
								{
									System.out.println ("errore di IO: " + e);
									return false;
								}
		boolean c=controllo (n, m, matrice);	//controllo che possa essere compatibile con una matrice di mosse
		if (c==false) return false;
		else {
				switch (pezzo) {		//faccio i casi in base al pezzo 
							case "re":
										return re (n, m, matrice);
							case "donna":
										return donna(n, m, matrice);
							case "alfiere":
										return alfiere(n, m, matrice);
							case "torre":
										return torre(n, m, matrice);
							case "cavallo":
										return cavallo(n, m, matrice);
							default:
										return false;
								}
			}	
	}

	public static void main(String[] args) {
			// Mini-test usando l'helper.
			Helper.generateMoveFile("good.txt");
			if (testChessMoves("good.txt")) 
				{
				System.out.println("Test 1 - PASS");
				} 
			else {
				System.out.println("Test 1 - FAIL !!!");
				}
		
			Helper.generateNonMoveFile("bad.txt");
			if (testChessMoves("bad.txt")) 
				{
				System.out.println("Test 2 - FAIL !!!");
				} 
			else 
				{
				System.out.println("Test 2 - PASS");
				}
	}
}