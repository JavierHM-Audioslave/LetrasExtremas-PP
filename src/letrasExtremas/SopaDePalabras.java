package letrasExtremas;

import java.io.*;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class SopaDePalabras {

	Palabra[] arrDePal;
	int cantPal;
	
	public SopaDePalabras()
	{
		File archIn;
		FileReader fr;
		BufferedReader br;
		try
		{
			archIn=new File(JOptionPane.showInputDialog("Ingrese el path completo del archivo de entrada"));
			fr=new FileReader(archIn);
			br=new BufferedReader(fr);
			cantPal=Integer.parseInt(br.readLine());
			arrDePal=new Palabra[cantPal];
			for(int i=0; i<cantPal; i++)
			{
				arrDePal[i]=new Palabra(br.readLine());
			}
			try
			{
				fr.close();
				br.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public void generarSalida()
	{
		ArrayList<String> arrAuxDePal=new ArrayList<String>();
		ArrayList<Integer> arrAuxDeFrec=new ArrayList<Integer>();
		
		int pos=0;
				
		for(int i=0; i<cantPal; i++)	// Este for arma el ArrayList con todas las letras INICIALES de todas las palabras y su consecuente ArrayList de frecuencia de cada una de esas letras. //
		{
			boolean yaExiste=false;
			for(int j=0; j<arrAuxDePal.size(); j++)
			{
				if(arrDePal[i].getPal().charAt(0)==arrAuxDePal.get(j).charAt(0))
				{
					yaExiste=true;
					arrAuxDeFrec.set(j, arrAuxDeFrec.get(j)+1);
					pos++;
					break;
				}
			}
			
			if(yaExiste==false)
			{
				arrAuxDePal.add(arrDePal[i].getPal().substring(0,1));
				arrAuxDeFrec.add(1);
				pos++;
			}
			
			yaExiste=false;
			
			for(int j=0; j<arrAuxDePal.size(); j++)		// Este for arma el ArrayList con todas las letras TERMINALES de todas las palabras y su consecuente ArrayList de frecuencia de cada una de esas letras. //
			{
				if( arrDePal[i].getPal().charAt(arrDePal[i].getPal().length()-1 )== arrAuxDePal.get(j).charAt( arrAuxDePal.get(j).length()-1 ))
				{
					yaExiste=true;
					arrAuxDeFrec.set(j, arrAuxDeFrec.get(j)+1);
					pos++;
					break;
				}
			}
			
			if(yaExiste==false)
			{
				arrAuxDePal.add(arrDePal[i].getPal().substring(arrDePal[i].getPal().length()-1, arrDePal[i].getPal().length()));
				arrAuxDeFrec.add(1);
				pos++;
			}			
		}
		
		ArrayList<String> masRepetidas=new ArrayList<String>(0);	// Es un ArrayList porque no sé si la letra mas repetida es una o mas de una. //
		int auxCantDeRep=0;
		
		for(int i=0; i<arrAuxDeFrec.size(); i++)	// En este for obtengo, en el ArrayList masRepetidas, el o los caracteres de mayor frecuencia. //
		{
			if(arrAuxDeFrec.get(i)==auxCantDeRep)
			{
				masRepetidas.add(arrAuxDePal.get(i));
			}
			
			if(arrAuxDeFrec.get(i)>auxCantDeRep)
			{				
				masRepetidas.clear();
				auxCantDeRep=arrAuxDeFrec.get(i);
				masRepetidas.add(arrAuxDePal.get(i));
			}						
		}
		
		
		ArrayList<String> arrDeSal=new ArrayList<String>(0);
		for(int i=0; i<cantPal; i++)	// En este for lleno el ArrayList arrDeSal el cual contiene las palabras cuyo/s caracter/es extremo/s concuerda/n con el/los caracter/es extremo/s encontrado/s en todo lo hecho arriba. //
		{
			for(String o:masRepetidas)
			{
				if(	arrDePal[i].getPal().charAt(0)==o.charAt(0) ) //Aclaracion: No tomo en cuenta el caso de que haya mas de un caracter extremo y una cierta palabra tenga como extremos a esos caracteres. En ese caso, en el archivo de salida va a aparecer dos veces dicha palabra. Tanto por el caracter extremo inicial como por el final. //
				{
					boolean yaExisteLaPalabra=false;
					for(String p:arrDeSal)
					{
						if(p.equals(arrDePal[i].getPal()))
						{
							yaExisteLaPalabra=true;
							break;
						}
					}
					
					if(yaExisteLaPalabra==false)
					{
						arrDeSal.add(arrDePal[i].getPal());
					}					
				}
				else
				{
					if( arrDePal[i].getPal().charAt(arrDePal[i].getPal().length()-1)==o.charAt(o.length()-1) )
					{
						boolean yaExisteLaPalabra=false;
						for(String p:arrDeSal)
						{
							if(p.equals(arrDePal[i].getPal()))
							{
								yaExisteLaPalabra=true;
								break;
							}
						}
						
						if(yaExisteLaPalabra==false)
						{
							arrDeSal.add(arrDePal[i].getPal());
						}
					}
				}
			}
		}
		
		try
		{
			File archSal=new File(JOptionPane.showInputDialog("Ingrese el path completo del archivo de salida"));
			FileWriter fw=new FileWriter(archSal);
			PrintWriter pw=new PrintWriter(fw);
			
			for(int i=0; i<masRepetidas.size(); i++)
			{
				pw.print(masRepetidas.get(i)+" ");
			}
			pw.println("");
			
			for(int i=0; i<arrDeSal.size(); i++)
			{
				pw.println(arrDeSal.get(i));
			}
			try
			{
				fw.close();
				pw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
