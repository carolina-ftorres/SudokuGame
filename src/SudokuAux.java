public class SudokuAux {

	public static final int SQUARE_SIZE = 50;
	public static final int BORDER_SIZE = 3;
	public static final int NUMBER_SIZE = 30;

	
	
	// 1
	static boolean SudokuValido(int[][] tabuleiro) {
		if(temRepeticaoLinha(tabuleiro) || temRepeticaoColuna(tabuleiro))
		return false;
		for (int i = 0; i < 9; i += 3)
			for (int j = 0; j < 9; j += 3)
				if (QuadradoV(tabuleiro, i, j) == false)
					return false;
		return true;
	}

	  static boolean temRepeticaoLinha(int[][] matriz){
	        for (int i = 0; i < 9; i++){
	            for (int j = 0; j < 9 - 1; j++){
	                if (matriz[i][j] != 0){
	                    for (int k = j + 1; k < matriz[i].length; k++){
	                        if (matriz[i][j] == matriz[i][k]){
	                            return true; // Número repetido na mesma linha
	                        }
	                    }
	                }
	            }
	        }
	        return false;
	    }

	  static boolean temRepeticaoColuna(int[][] matriz){
	        for (int i = 0; i < 9-1; i++){
	            for (int j = 0; j < 9; j++){
	                if (matriz[i][j] != 0){
	                    for (int k = i + 1; k < matriz.length; k++){
	                        if (matriz[i][j] == matriz[k][j]) {
	                            return true; // Número repetido na mesma coluna
	                        }
	                    }
	                }
	            }
	        }
	        return false;
	    }
	    
	    
	static boolean QuadradoV(int[][] tabuleiro, int inicioLinha, int inicioColuna){
	        boolean[] visto = new boolean[9]; 
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++){
	                int numero = tabuleiro[inicioLinha + i][inicioColuna + j];
	                if (numero != 0) {
	                    if (visto[numero-1]){
	                        return false;
	                    }
	                    visto[numero-1] = true;
	                }
	            }
	        }
	        return true;
	    }
	

	//2
	static int[][] generateZerosBoard(int[][] m, int percentage){
        double doublePercentage = percentage / 100.00;
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                if (Math.random() < doublePercentage)
                    m[i][j] = 0;
        return m;
    }

	//3
    static String printBoard(int[][] m){
        String out  = "";
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                if (j == m[0].length - 1){
                    out += m[j][i] + "\n";
                }else{
                    out += m[j][i] + " ";
                }
        return out;
    }
    
    //Desenhar
    static void drawLineHorizontal(ColorImage img, int x,  int y, Color color, int espessura, int tamanho){
        for (int j = y; j < y + espessura; j++)
            for(int i = x; i < tamanho+x; i++)
                img.setColor(i,j,color);
    }

    static void drawLineVertical(ColorImage img, int x, int y, Color color, int espessura, int tamanho){
        for (int j = x; j < x + espessura; j++)
            for(int i = y; i < tamanho+y; i++)
                img.setColor(j,i,color);
    }
    
    //4
     static ColorImage drawBoard(){
        int totalLines = 8;
        int squareSide = 6 * BORDER_SIZE + 2 * BORDER_SIZE + 9 * SQUARE_SIZE;
        ColorImage img = new ColorImage(squareSide, squareSide, Param.SQUARE_BACKCOLOR);
        for(int i = 1; i < totalLines + 1; i++){
            if(i % 3 != 0){
            drawLineHorizontal(img,0, i * SQUARE_SIZE + (i-1) * BORDER_SIZE, Param.GRID_COLOR, BORDER_SIZE, img.getWidth());
            drawLineVertical(img, i * SQUARE_SIZE + (i-1) * BORDER_SIZE,0, Param.GRID_COLOR, BORDER_SIZE, img.getWidth());
            }
        }
        for(int i = 1; i < totalLines + 1; i++){
            if(i % 3 == 0){
            drawLineHorizontal(img,0, i * SQUARE_SIZE + (i-1) * BORDER_SIZE, Param.SEGMENT_GRID_COLOR, BORDER_SIZE, img.getWidth());
            drawLineVertical(img, i * SQUARE_SIZE + (i-1) * BORDER_SIZE,0, Param.SEGMENT_GRID_COLOR, BORDER_SIZE, img.getWidth());
            }
        }
       return img;
    }
     
     static void updateBoard(ColorImage img){
         int totalLines = 8;
         int squareSide = 6 * BORDER_SIZE + 2 * BORDER_SIZE + 9 * SQUARE_SIZE;
         
         for(int i = 1; i < totalLines + 1; i++){
             if(i % 3 != 0){
             drawLineHorizontal(img,0, i * SQUARE_SIZE + (i-1) * BORDER_SIZE, Param.GRID_COLOR, BORDER_SIZE, img.getWidth());
             drawLineVertical(img, i * SQUARE_SIZE + (i-1) * BORDER_SIZE,0, Param.GRID_COLOR, BORDER_SIZE, img.getWidth());
             }
         }
         for(int i = 1; i < totalLines + 1; i++){
             if(i % 3 == 0){
             drawLineHorizontal(img,0, i * SQUARE_SIZE + (i-1) * BORDER_SIZE, Param.SEGMENT_GRID_COLOR, BORDER_SIZE, img.getWidth());
             drawLineVertical(img, i * SQUARE_SIZE + (i-1) * BORDER_SIZE,0, Param.SEGMENT_GRID_COLOR, BORDER_SIZE, img.getWidth());
             }
         }
    
     }

    //5
    static void insertNumberInBoard(ColorImage board, int number, int x, int y){
        int boardX;
        int boardY;
        if(x > 0){
            boardX = BORDER_SIZE * x + SQUARE_SIZE * x + SQUARE_SIZE / 2;
        }else{
            boardX = SQUARE_SIZE / 2;
        }
        if(y > 0){
            boardY = BORDER_SIZE * y + SQUARE_SIZE * y + SQUARE_SIZE / 2;
        }else{
            boardY = SQUARE_SIZE / 2;
        }
        int squareX = BORDER_SIZE * x + SQUARE_SIZE * x;
        int squareY = BORDER_SIZE * y + SQUARE_SIZE * y;
       
        for(int i = squareX; i < squareX + SQUARE_SIZE; i++)
            for(int j = squareY; j < squareY + SQUARE_SIZE; j++)
              board.setColor(i, j, Param.SQUARE_BACKCOLOR);
        if(number!=0){
        board.drawCenteredText(boardX, boardY, Integer.toString(number), NUMBER_SIZE, Param.TEXTCOLOR);
        }
    }
        
	
    //6
    public static void drawCountourLine(ColorImage board, int indiceLinha){

        int linhaVertical1X = 0;
        int linhaVertical2X = board.getWidth() - BORDER_SIZE;
        int linhaHorinzontal1Y = BORDER_SIZE * indiceLinha + SQUARE_SIZE * indiceLinha;
        int linhaHorinzontal2Y = BORDER_SIZE * indiceLinha + SQUARE_SIZE * (indiceLinha + 1) - BORDER_SIZE;

        int linhaHorizontalTamanho = board.getWidth();
        int linhaVerticalTamanho = SQUARE_SIZE - BORDER_SIZE;

        drawLineHorizontal(board,0, linhaHorinzontal1Y, Param.OUTLINE_COLOR , BORDER_SIZE, linhaHorizontalTamanho);
        drawLineHorizontal(board,0, linhaHorinzontal2Y, Param.OUTLINE_COLOR , BORDER_SIZE, linhaHorizontalTamanho);
        drawLineVertical(board, linhaVertical1X, linhaHorinzontal1Y, Param.OUTLINE_COLOR, BORDER_SIZE,linhaVerticalTamanho);
        drawLineVertical(board, linhaVertical2X, linhaHorinzontal1Y, Param.OUTLINE_COLOR, BORDER_SIZE, linhaVerticalTamanho);

    }
    
    //7
    public static void drawCountourColumn(ColorImage board, int indiceLinha){

        int linhaHorinzontal1Y = 0;
        int linhaHorinzontal2Y = board.getWidth() - BORDER_SIZE;
        int linhaVertical1X = BORDER_SIZE * indiceLinha + SQUARE_SIZE * indiceLinha;
        int linhaVertical2X = BORDER_SIZE * indiceLinha + SQUARE_SIZE * (indiceLinha + 1) - BORDER_SIZE;

        int linhaHorizontalTamanho = SQUARE_SIZE - BORDER_SIZE;
        int linhaVerticalTamanho = board.getWidth();
        
        drawLineVertical(board, linhaVertical1X, 0, Param.OUTLINE_COLOR, BORDER_SIZE, linhaVerticalTamanho);
        drawLineVertical(board, linhaVertical2X, 0, Param.OUTLINE_COLOR, BORDER_SIZE, linhaVerticalTamanho);
        drawLineHorizontal(board, linhaVertical1X, linhaHorinzontal1Y, Param.OUTLINE_COLOR, BORDER_SIZE, linhaHorizontalTamanho);
        drawLineHorizontal(board, linhaVertical1X, linhaHorinzontal2Y, Param.OUTLINE_COLOR, BORDER_SIZE, linhaHorizontalTamanho);
    }
    
    //8 
    public static void drawCountourSquare(ColorImage board, int x, int y){
    	int linhaHorinzontal1Y = BORDER_SIZE * 3 * x + SQUARE_SIZE * 3 * x ;
        int linhaHorinzontal2Y = SQUARE_SIZE * 3 * (x+1) + BORDER_SIZE * 2 * (x+1) + BORDER_SIZE * (x-1);
         int linhaVertical1X = BORDER_SIZE * 3 * y + SQUARE_SIZE * 3 * y;
         int linhaVertical2X = SQUARE_SIZE * 3 * (y+1) + BORDER_SIZE * 2 * (y+1) + BORDER_SIZE * (y-1);
         
         int tamanho = BORDER_SIZE * 2 + SQUARE_SIZE * 3;
        		 
         drawLineHorizontal(board, linhaVertical1X, linhaHorinzontal1Y, Param.OUTLINE_COLOR, BORDER_SIZE, tamanho);
         drawLineHorizontal(board, linhaVertical1X, linhaHorinzontal2Y, Param.OUTLINE_COLOR, BORDER_SIZE, tamanho);
         drawLineVertical(board, linhaVertical1X, linhaHorinzontal1Y, Param.OUTLINE_COLOR, BORDER_SIZE, tamanho);
         drawLineVertical(board, linhaVertical2X, linhaHorinzontal1Y, Param.OUTLINE_COLOR, BORDER_SIZE, tamanho); 

    }
   
	
    //outras funções
    
    // ajudar no reset da parte 2
    static int[][] copiar(int [][] imatrix){
		int [][] copia = new int [imatrix.length][imatrix[0].length];
		for (int i = 0; i < imatrix.length; i++)
				for (int j = 0; j < imatrix[0].length; j++)
		copia [i][j] = imatrix[i][j];
		return copia;
	}
    
    static void modificar(int [][] matrix1, int [][] matriz2){
		for (int i = 0; i < matrix1.length; i++)
				for (int j = 0; j < matrix1[0].length; j++)
					matrix1[i][j] = matriz2 [i][j];
	}
    
    
    //transpor
    static int[][] transposta(int[][] m) {
		int[][] t = new int[m[0].length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				t[j][i] = m[i][j];
			}
		}
		return t;
	}
    
     static boolean temRepeticaoSegmento(int[][] m){
		for(int i=0; i<9; i+=3)
			for(int j=0; j<9; j+=3)
				if(SudokuAux.QuadradoV(m,j,i)==false){
					return true;
				}
		return false;
	}
	
    
   

}