public class SudokuBoard {

	public int [][] gamematrix;
	public int [][] imatrix;
	
	public int[] xPositions;
	public int[] yPositions;
	public int currentIndex = 0;
	
	//PARTE 2========================
	//1
	SudokuBoard (int [][] matrix, int percentagem) {
		if(!SudokuAux.SudokuValido(matrix))
			throw new IllegalArgumentException ("Invalid SudokuBoard");
		
		SudokuAux.generateZerosBoard(matrix ,percentagem);
		gamematrix = matrix;
		imatrix = SudokuAux.copiar(matrix);
		xPositions = new int[81];
		yPositions = new int[81];
	}
	
	//2
	int cNumber( int x, int y){
		int number = gamematrix[x][y];
		return number;
	}
	
	//3
	void move( int x , int y , int num){
		if (gamematrix[x][y] == 0 && x>=0 && x<gamematrix.length && y>=0 && y<gamematrix.length ){
			xPositions[currentIndex]=x;
			yPositions[currentIndex]=y;
			currentIndex++;
			gamematrix[x][y] = num;
		}else{
		throw new IllegalArgumentException ("Jogada Não É Válida");
	    }
	}
	
	
	//4
	void aleatorioMove(){
		int number = 0;
		int x = (int) (Math.random()*9);
		int y = (int) (Math.random()*9); 
		if (gamematrix[x][y] == 0){
			do{
				number = (int) (Math.random()*9 + 1);
				gamematrix[x][y] = number;
			}
			while (!SudokuAux.SudokuValido(gamematrix));
				System.out.println("Movimento aleatório na "+"linha:" + x + " coluna:" + y + "  O número jogado foi: " + number);
				xPositions[currentIndex]=x;
				yPositions[currentIndex]=y;
				currentIndex++;
		}
		else
			aleatorioMove();
	}
	
	//5
	void reset(){
		SudokuAux.modificar(gamematrix, imatrix);
		for(int i=0; i<currentIndex; i++){
			xPositions[i]=0;
			yPositions[i]=0;
		}
			currentIndex=0;
	}
	
	//6
	int [] RepeticaoSegmentoIndex(){
		int[] resultado = new int [2];
		for(int i=0; i<9; i+=3)
			for(int j=0; j<9; j+=3)
				if(SudokuAux.QuadradoV(gamematrix,i,j)==false){
					resultado[0]=i/3;
					resultado[1]=j/3;
					return resultado;
				}
		resultado[0]=-1;
		resultado[1]=-1;
		return resultado;
	}
	
	//7
	int RepeticaoLinhaIndex(){
	        for (int i = 0; i < 9; i++){
	            for (int j = 0; j < 9 - 1; j++){
	                if (gamematrix[i][j] != 0){
	                    for (int k = j + 1; k < gamematrix[i].length; k++){
	                        if (gamematrix[i][j] == gamematrix[i][k]){
	                            return i; 
	                        }
	                    }
	                }
	            }
	        }
	        return -1;
	    }

	  int RepeticaoColunaIndex(){
	        for (int i = 0; i < 9-1; i++){
	            for (int j = 0; j < 9; j++){
	                if (gamematrix[i][j] != 0){
	                    for (int k = i + 1; k < gamematrix.length; k++){
	                        if (gamematrix[i][j] == gamematrix[k][j]) {
	                            return j; 
	                        }
	                    }
	                }
	            }
	        }
	        return -1;
	    }
	
	//8
	boolean iscomplete(int [][] matrix){
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				if(matrix[i][j]==0)
					return false;
		return true;
	}
	
	//9
	void undo(){
		if (currentIndex - 1 < 0)
			throw new IllegalStateException("Não existem mais jogadas para voltar");
		int x = xPositions[currentIndex - 1];
		int y = yPositions[currentIndex - 1];
		gamematrix[x][y] = 0;
		currentIndex--;
	}
	
	
}