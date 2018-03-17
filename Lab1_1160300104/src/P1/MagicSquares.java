package P1;
import java.io.*;


public class MagicSquares{
	
	public static boolean generateMagicSquare(int n) {
		if((n<0) || (n%2 == 0) )
		{
			System.out.println("输入不合法");
			return false;
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		FileWriter writer;
		 try {
		writer = new FileWriter("F:\\Lab1_1160300104\\src\\P1\\txt\\6.txt");
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
			{
				System.out.print(magic[i][j] + "\t");
				writer.write(magic[i][j]+"\t");
			}
				writer.write("\r\n");
				System.out.println();
		}
			writer.close();
		 } catch (IOException e) {
	            e.printStackTrace();
	        }
		return true;
	}
    public static boolean isLegalMagicSquare(String fileName) {
        int[][] a = null;
        int order = 0;
        int inorder = 0;
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int rowNum = 0;
            String[] tempRow;
            boolean isCreateSquare=false;
            while ((tempString = reader.readLine()) != null) {
            	if(tempString.indexOf(" ")!=-1)
                {
                	System.out.println("数字之间没用制表符");
                	return false;
                }
                tempRow = tempString.split("\t");
                if(!isCreateSquare){
                    order=tempRow.length;
                    a=new int[order][order];
                    isCreateSquare=true;
                    inorder = order;
                }
                inorder = tempRow.length;
                if(inorder == order)
                {
                    for(int i=0;i<order;i++){
                        for (int j = 0; j < tempRow[i].length(); j++) {  
                            if (!Character.isDigit(tempRow[i].charAt(j))) {
                            	System.out.println("矩阵中有非整数类型");
                                return false;  
                            }
                        }
                        	 a[rowNum][i]=Integer.parseInt(tempRow[i]);
                        	 if(a[rowNum][i] < 0)
                        	 {
                        		 System.out.println("矩阵中有负整数");
                        		 return false;
                        	 }     
                    }
                }
                else
                {
                	System.out.println("行列数不同");
                	return false;
                }
                rowNum++;
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        int[] b=new int[order*2+2];
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                b[i] +=a[i][j];
            }
        }
        for(int i=0; i<order; i++){
            for(int j=0; j<order; j++){
                b[i+order] +=a[j][i];
            }
        }
        for(int i=0;i<order;i++)
        {
            for(int j=0;j<order;j++)
            {
                if(i == j) b[b.length-2] += a[i][j];
                if(j == order-i-1) b[b.length-1] += a[i][j];
            }
        }
        for(int i=1;i<b.length;i++)
        {
            if(b[i] != b[0])
            {
            	System.out.println("和不同");
                return false;
            }
        }
        return true;
    }
	public static void main(String[] args) {
        System.out.println(isLegalMagicSquare("F:\\Lab1_1160300104\\src\\P1\\txt\\1.txt"));
        System.out.println(isLegalMagicSquare("F:\\Lab1_1160300104\\src\\P1\\txt\\2.txt"));
        System.out.println(isLegalMagicSquare("F:\\Lab1_1160300104\\src\\P1\\txt\\3.txt"));
        System.out.println(isLegalMagicSquare("F:\\Lab1_1160300104\\src\\P1\\txt\\4.txt"));
        System.out.println(isLegalMagicSquare("F:\\Lab1_1160300104\\src\\P1\\txt\\5.txt"));
        generateMagicSquare(5);
        System.out.println(isLegalMagicSquare("F:\\Lab1_1160300104\\src\\P1\\txt\\6.txt"));
    }
}