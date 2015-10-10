
/**
 * Write a description of class Individuo here.
 * 
 * @author (Diego Gadens dos Santos) 
 * @version (10/04/2008)
 */
public class Individuo
{
    

    
    public double cromossomo[];
    
    private double fitness;
    
    private double distFitness;
    
    private double diferen�a;
 
    
    public Individuo(int leiteAux, int a��carAux, double manteigaAux, double amidoAux, int tempo1Aux, int tempo2Aux)
    {
        cromossomo = new double[6];
        cromossomo[0] = leiteAux;
        cromossomo[1] = a��carAux;
        cromossomo[2] = manteigaAux;
        cromossomo[3] = amidoAux;
        cromossomo[4] = tempo2Aux;
        cromossomo[5] = tempo1Aux;
        
        
        //calculaFitness();
    }

    public void calculaFitness()
    {
        fitness = (((cromossomo[0]*0.15)*cromossomo[5])+(((cromossomo[1]*0.45)+(cromossomo[2]*0.1)+(cromossomo[3]*0.3))*cromossomo[4]));
        
        
       calculaDist�ncia();
    }
    
    
    
    
    
    
    
    
    
    public void imprimeFitness()
    {
       {
        System.out.println("fitness: " + fitness);
        System.out.println("\n");
       }
    }
    
    public void imprimeDados()
    {
     //System.out.printf ("Adicione %.0f mililitros de leite em uma panela.\n", cromossomo[0]);
     //System.out.printf ("Ferva o leite em fogo alto por %.0f minutos \n", cromossomo[5]);
     //System.out.printf ("Adicione %.0f gramas de a��car, %.1f gramas de manteiga derretida e %.1f gramas de amido. \n", cromossomo[1], cromossomo[2], cromossomo[3]);
     //System.out.printf ("Cozinhe esta mistura por mais %.0f minutos. \n\n\n", cromossomo[4]);

     
     System.out.printf("Leite: %.0f mililitros\n", cromossomo[0]);
     System.out.printf("A��car: %.0f gramas.\n", cromossomo[1]);
     System.out.printf("Manteiga: %.1f gramas.\n", cromossomo[2]);
     System.out.printf("Amido: %.1f gramas.\n", cromossomo[3]);
     System.out.printf("Tempo 1: %.0f minutos.\n", cromossomo[4]);
     System.out.printf("Tempo2: %.0f minutos.\n", cromossomo[5]);
     System.out.println("Diferen�a: " + diferen�a);
     System.out.printf("Distancia: %.0f \n", distFitness);
     
     
     
     
     
     
     //System.out.println("Leite: " + cromossomo[0]);   
     //System.out.println("A��car: " + cromossomo[1]);
     //System.out.println("Manteiga: " + cromossomo[2]);
     //System.out.println("Amido: " + cromossomo[3]);
     //System.out.println("Tempo 1: " + cromossomo[4]);
     //System.out.println("Tempo 2: " + cromossomo[5]);
     //System.out.println("\n");     
    }
    
    public double getFitness()
    {
        return fitness;
    }
    
    public void calculaDist�ncia()
    {
        if (fitness < 600)
            distFitness = (600 - fitness);
        else
            distFitness = (fitness - 600);      
    }
    
    public double getDist�ncia()
    {
        return distFitness;
    }
    
    public double getDiferen�a()
    {
        return diferen�a;
    }
    
    public void setFitness(double x)
    {
        fitness = x;
    }
   
    public void setDiferen�a(double x)
    {
        diferen�a = x;
    }
    
    
    
}
