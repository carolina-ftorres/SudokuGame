import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

class Sudoku {
	
	public SudokuBoard board;
	public ColorImage img;
	public boolean erro;
	
	public Sudoku(String boardPath, int difficulty){
		
		this.board = new SudokuBoard(readSudFile(boardPath),difficulty);
		img = SudokuAux.drawBoard();
		getImageFromBoard();	
	}
	
	
	private int [][] readSudFile(String file){// tranformar ficheiro em matriz
		int[][] m = new int[9][9];
		try{
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNext()){
				for(int i=0; i<m.length; i++){
					for(int j=0; j<m.length; j++){
						int n = scanner.nextInt();
					    m[j][i] = n;
					}
				}
			}
			scanner.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		return SudokuAux.transposta(m);
	}
	
	private void loadSudFile(String file){
		try{
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNext()){
				for(int i=0; i<board.imatrix.length; i++){
					for(int j=0; j<board.imatrix.length; j++){
						int n = scanner.nextInt();
						board.imatrix[i][j] = n;
					}
				}
				for(int i=0; i<board.gamematrix.length; i++){
					for(int j=0; j<board.gamematrix.length; j++){
						int n = scanner.nextInt();
						board.gamematrix[i][j] = n;
					}
				}
			}
			scanner.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	private void getImageFromBoard(){
		SudokuAux.updateBoard(img);
		for(int i = 0; i < board.gamematrix.length; i++)
			for(int j = 0; j < board.gamematrix[0].length; j++)
				SudokuAux.insertNumberInBoard(img, board.gamematrix[j][i], i, j);
		
	}

	public void play(int linha, int coluna, int n){
		if(erro){
		  throw new IllegalStateException("Erro no board, dá undo");
		  }
		board.move(linha,coluna,n);
		if (SudokuAux.SudokuValido(board.gamematrix)){
			getImageFromBoard();
			System.out.println("Movimento na "+"linha:" + linha + "  coluna:" + coluna + "  " + n);
			  	if (board.iscomplete(board.gamematrix)){
			  		System.out.println("Parabéns Completou o Jogo !!");   
			}
		}else{
			getImageFromBoard();
			System.out.println("Movimento Inválido, Anule a Jogada");
			Invalidos();
	    }
	}
	
	
	
	private void Invalidos(){
		erro = true;
		if(SudokuAux.temRepeticaoLinha(board.gamematrix)){
			int n=board.RepeticaoLinhaIndex();
			SudokuAux.drawCountourLine(img, n);
		}
		if(SudokuAux.temRepeticaoColuna(board.gamematrix)){
			int m=board.RepeticaoColunaIndex();
			SudokuAux.drawCountourColumn(img, m);
		}
		if(SudokuAux.temRepeticaoSegmento(board.gamematrix)){
			int[] q=board.RepeticaoSegmentoIndex();
			SudokuAux.drawCountourSquare(img, q[0],q[1]);
		}
	}
	
	
	public void random_play(){
		if(erro){
			  throw new IllegalStateException("Erro no board, dá undo");
	    }
		board.aleatorioMove();
		if (SudokuAux.SudokuValido(board.gamematrix)){
			getImageFromBoard();
			 	if (board.iscomplete(board.gamematrix)==true){
					System.out.println("Parabéns Completou o Jogo !!");   
				}
		}else{
			throw new IllegalArgumentException ("Invalid SudokuBoard");
		}
	}
	
	public void undo(){
		board.undo();
	    erro = false;
		if (SudokuAux.SudokuValido(board.gamematrix)){
			getImageFromBoard();
			System.out.println("Jogada Anulada");
		}else{
			throw new IllegalArgumentException ("Invalid SudokuBoard");
		}
	}
	
	public void reset(){
	board.reset();
	erro = false;
	getImageFromBoard();
	System.out.println("O Jogo Foi Reiniciado");
	}
	
	public void load(String ficheiro){
		loadSudFile(ficheiro);
		erro = false;
		getImageFromBoard();
		System.out.println("Novo Jogo Carregado");
		board.currentIndex = 0;
		for (int i = 0; i<board.xPositions.length; i++){
			board.xPositions[i]=0;
			board.yPositions[i]=0;
		}
	}
	
	
    
    public void save(String novo_file){
    	if(erro){
  		  throw new IllegalStateException("Erro no board, dá undo");
  		}
    	String in = SudokuAux.printBoard(SudokuAux.transposta(board.imatrix));
    	String out = SudokuAux.printBoard(SudokuAux.transposta(board.gamematrix));
		try{
		PrintWriter writer = new PrintWriter(new File(novo_file));
		writer.println (in);
		writer.println (out);
		writer.close();
		System.out.println("Jogo Salvo");
		}
		catch(FileNotFoundException e){
			System.err.println("erro escrita ficheiro");
		}
	}
    
	
	
   
}