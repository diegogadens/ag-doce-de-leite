import java.util.Random;
import java.util.Arrays; 

public class Principal
{
    Random geradorAleatório;
    Individuo populaçãoInicial[];
    Individuo populaçãoSelecionada[];
    Individuo ordenador;
    Individuo ranking[];
    int geração = 0;
    public int tamanhoPopulação;
    public double taxaCross;
    public double taxaMutação;
    public int numGerações;
    
    
    public Principal(int tamanhoPopulação, double taxaCross, double taxaMutação, int numGerações)
    {
        
        this.tamanhoPopulação = tamanhoPopulação;
        this.taxaCross = taxaCross;
        this.taxaMutação = taxaMutação;
        this.numGerações = numGerações;
        
        geradorAleatório = new Random();
        populaçãoInicial = new Individuo[tamanhoPopulação];
        populaçãoSelecionada = new Individuo[tamanhoPopulação];
        ordenador = new Individuo(0,0,0,0,0,0);
        ranking = new Individuo[tamanhoPopulação];
        
        
        
        geraPopulaçãoInicial();
        System.out.println("Gerou população inicial");
        avaliaPopulação();
        System.out.println("Avaliou População Inicial");
              
        
        System.out.println("vai ordenar...");
        ordena();
        System.out.println("Ordenou");
        
        geração++;
       // System.out.println("teste da ordenação");
       // for(int i=0; i<tamanhoPopulação; i++)
       // {
       //     System.out.println(i+"°: "+ populaçãoInicial[i].getDistância());
       // }
           
         imprime();   
       
       while (geração < numGerações)
      
        {
       //    try 
       //    {
       //        Thread.sleep(3000);
       //    } 
       //    catch (InterruptedException ex) 
       //    {} 
           System.out.println("Vai gerar a nova População");
           próximaGeração();
           System.out.println("Gerou a nova população");
           
           //for(int i=1; i<tamanhoPopulação; i++)
           //{
           // if ( populaçãoInicial[i].getFitness() == populaçãoInicial[i-1].getFitness())
           // {
           //     populaçãoInicial[i] = new Individuo(randLeite(), randAçúcar(), randManteiga(), randAmido(), randTempo1(), randTempo2());
                //populaçãoInicial[i-1] = new Individuo(0, 0, 0, 0, 0, 0);
           //    populaçãoInicial[i].calculaFitness();
           //  }
           //}
           
           System.out.println("Vai avaliar a nova população");
           avaliaPopulação();
           System.out.println("Avaliou a nova população");
           System.out.println(geração+"ª geração");
           System.out.println("Vai ordenar a nova população");
           ordena();
           System.out.println("Ordenou a nova população");
           System.out.println("\n\n");
           
           System.out.println("Busca Local");
           buscaLocal();
           System.out.println("\n\n");
           
           System.out.println("Vai ordenar a nova população");
           ordena();
           System.out.println("Ordenou a nova população");
           System.out.println("\n\n");
           
           //for(int i=0; i<tamanhoPopulação; i++)
           //{
           // System.out.println(i+"°: "+ populaçãoInicial[i].getDistância());
           //}
           geração++;
           imprime();
           
        
           

       }
    }


    public void geraPopulaçãoInicial()
    {
        for(int i=0; i<tamanhoPopulação; i++)
        {
            populaçãoInicial[i] = new Individuo(randLeite(), randAçúcar(), randManteiga(), randAmido(), randTempo1(), randTempo2());
            populaçãoInicial[i].setDiferença((populaçãoInicial[i].cromossomo[1] + populaçãoInicial[i].cromossomo[2] + populaçãoInicial[i].cromossomo[3])/populaçãoInicial[i].cromossomo[0]);
            
            //ranking[i] = populaçãoInicial[i].getFitness();
        }
    }
    
    
    public void próximaGeração()
    {
        System.out.println("Inicia nova População.");
        for(int i=0; i<(tamanhoPopulação*0.3); i++)
        {
            /*Pega os 30% melhores*/
            populaçãoSelecionada[i] = populaçãoInicial[i];
        } 
        
        for(int i=(int)(tamanhoPopulação*0.3); i<(tamanhoPopulação*0.45); i++)
        {
            /*Pega os 15% piores*/
            populaçãoSelecionada[i] = populaçãoInicial[i];
        }    
        
        for(int i=(int)(tamanhoPopulação*0.45); i<(tamanhoPopulação*(0.45+taxaCross)); i++)
        {
            /*Pega 20 gerados por cross da pop Inicial*/
            //populaçãoSelecionada[i] = crossover();
            crossover(i);
            //populaçãoSelecionada[i] = new Individuo(randLeite(), randAçúcar(), randManteiga(), randAmido(), randTempo1(), randTempo2());
        }
        
        for(int i=(int)(tamanhoPopulação*(0.45+taxaCross)); i<(tamanhoPopulação*(0.45+taxaCross+taxaMutação)); i++)
        {
            /*pega 2 individuos gerados por mutação da Pop Inicial*/
            mutação(i);
        }
        
        for(int i=(int)(tamanhoPopulação*(0.45+taxaCross+taxaMutação)); i<tamanhoPopulação; i++)
        {
            populaçãoSelecionada[i] = new Individuo(randLeite(), randAçúcar(), randManteiga(), randAmido(), randTempo1(), randTempo2());
        }
       
       // System.out.println("populaçãoInicial = populaçãoSelecionada.");
        
        
        populaçãoInicial = populaçãoSelecionada;

        
        
        //System.out.println("Começa Ordenação nova população");
        
       // System.out.println("Terminou a nova população");   
  
        //for(int i=0; i<tamanhoPopulação; i++)
        //{
            //populaçãoInicial[i].getFitness() = populaçãoSelecionada[i].getFitness();
            //System.out.println("vai ordenar na " + geração + " geração");
            //Arrays.sort(ranking);
            //System.out.println(populaçãoSelecionada[i].getFitness());
       // }
    }    
    
    //public Individuo crossover()
    public void crossover(int z)
    {
        int pai_1, pai_2, ponto;
        Individuo individuoAux = new Individuo(0, 0, 0, 0, 0, 0);
        
        pai_1 = geradorAleatório.nextInt(tamanhoPopulação/2);
        //pai_2 = (tamanhoPopulação/2 + geradorAleatório.nextInt(tamanhoPopulação/2));
        pai_2 = (geradorAleatório.nextInt(tamanhoPopulação));        
        
        System.out.println("Pai_1: "+pai_1);
        System.out.println("Fitness: "+populaçãoInicial[pai_1].getFitness()+"\n");
        populaçãoInicial[pai_1].imprimeDados();

        System.out.println("Pai_2: "+pai_2);
        System.out.println("Fitness: "+populaçãoInicial[pai_2].getFitness()+"\n");
        populaçãoInicial[pai_2].imprimeDados();
       
        
        ponto = (1 + geradorAleatório.nextInt(5));
        
        for(int i = 0; i<ponto; i++)
        {
            individuoAux.cromossomo[i] = populaçãoInicial[pai_1].cromossomo[i];
        }
        
        for(int i = ponto; i<6; i++)
        {
            individuoAux.cromossomo[i] = populaçãoInicial[pai_2].cromossomo[i];
        }

        individuoAux.calculaFitness();
        
        System.out.println("Depois do cross: ");
        System.out.println("Fitness: "+individuoAux.getFitness()+"\n");
        individuoAux.imprimeDados();
        
        populaçãoSelecionada[z] = individuoAux;
        
    }
    
   
   public void mutação(int z)
   {
        int individuo, gene;
        individuo = geradorAleatório.nextInt(tamanhoPopulação);
        gene = geradorAleatório.nextInt(6);
        //Individuo individuoAux = populaçãoInicial[individuo];
        
        
        System.out.println("Individuo Mutado: "+individuo);
        System.out.println("Fitness antes: "+populaçãoInicial[individuo].getFitness());
        //populaçãoInicial[individuo].imprimeDados();
        
        System.out.println("Gene: "+gene+" Antes da mutação: " + populaçãoInicial[individuo].cromossomo[gene]+"\n");
        switch(gene)
        { 
            case 0 :  
                populaçãoInicial[individuo].cromossomo[gene] = randLeite();  
            break;  
   
            case 1 :  
                populaçãoInicial[individuo].cromossomo[gene] = randAçúcar();  
            break;  
   
            case 2 :  
                populaçãoInicial[individuo].cromossomo[gene] = randManteiga();  
            break;  
   
            case 3 :  
                populaçãoInicial[individuo].cromossomo[gene] = randAmido();  
            break;  
            
            case 4 :  
                populaçãoInicial[individuo].cromossomo[gene] = randTempo1();  
            break;  
   
            case 5 :  
                populaçãoInicial[individuo].cromossomo[gene] = randTempo2();  
            break;  
        }
        populaçãoInicial[individuo].calculaFitness();
        //System.out.print("Individuo Mutado: "+individuo+" Depois da mutação: ");
        System.out.println("Fitness depois: "+populaçãoInicial[individuo].getFitness());
        //populaçãoInicial[individuo].imprimeDados();
        System.out.println("Gene: "+gene+" Depois da mutação: " + populaçãoInicial[individuo].cromossomo[gene]+"\n");
     
        populaçãoSelecionada[z] = populaçãoInicial[individuo];
   }
    
    
    public void ordena()
    {
       for(int i=0; i<tamanhoPopulação; i++)
         {
            for(int j=0; j<tamanhoPopulação-1; j++)
            {
                if(populaçãoInicial[j].getDistância() > populaçãoInicial[j+1].getDistância())
                {
                   ordenador = populaçãoInicial[j];
                   populaçãoInicial[j] = populaçãoInicial[j+1];
                   populaçãoInicial[j+1] = ordenador;
                }
            }
        } 
   }

   
   public void imprime()
   {
       System.out.println(geração + "ª Geração \n");
           try 
           {
               Thread.sleep(2);
           } 
           catch (InterruptedException ex) 
           {}
       for(int i=0; i<tamanhoPopulação; i++)
       {
           if((populaçãoInicial[i].getFitness() > 597) && (populaçãoInicial[i].getFitness() < 603))
           {
               System.out.println();
               System.out.printf("Fitness: %.4f \n", populaçãoInicial[i].getFitness());
               populaçãoInicial[i].imprimeDados();

           }
       }
    }
    
    
    public void avaliaPopulação()
    {
        for(int i=0; i<tamanhoPopulação; i++)
        {
            populaçãoInicial[i].calculaFitness();
            populaçãoInicial[i].calculaDistância();
            populaçãoInicial[i].setDiferença((populaçãoInicial[i].cromossomo[1] + populaçãoInicial[i].cromossomo[2] + populaçãoInicial[i].cromossomo[3])/populaçãoInicial[i].cromossomo[0]);
            //poulaçãoSelecionada[i] = populaçãoInicial[i];
        }
    }
    
    
    public void buscaLocal()
    {
        // verifica os individuos com fitness bom porém com receitas não-ideais
        for(int i=0; i<tamanhoPopulação; i++)
        {
            populaçãoInicial[i].setDiferença((populaçãoInicial[i].cromossomo[1] + populaçãoInicial[i].cromossomo[2] + populaçãoInicial[i].cromossomo[3])/populaçãoInicial[i].cromossomo[0]);
            //System.out.println("diferença do "+ i +": "+populaçãoInicial[i].getDiferença());
            //System.out.println(populaçãoInicial[i].cromossomo[1] +" + "+ populaçãoInicial[i].cromossomo[2] +" + "+ populaçãoInicial[i].cromossomo[3]+"  "+populaçãoInicial[i].cromossomo[0]);
            //System.out.println("Diferença: " + populaçãoInicial[i].getDiferença());
            
            
            
            
            
            
                if(populaçãoInicial[i].getDiferença() < 0.15 && populaçãoInicial[i].getDistância() <= 50)
                {
                    //tenta perturbá-los 3 vezes, se a perturbação funcionar Ok, senão aplica uma penalidade e o faz cair no ranking
                    System.out.println("Tenta perturbar o " + i);
                    if(perturbação(i) == false)
                        populaçãoInicial[i].setFitness(populaçãoInicial[i].getFitness()-(populaçãoInicial[i].getFitness()*populaçãoInicial[i].getDiferença()));
                        
                }
                else
                if(populaçãoInicial[i].getDiferença() > 0.25  && populaçãoInicial[i].getDistância() <= 50)
                {
                    System.out.println("Tenta perturbar o " + i);
                    if(perturbação(i) == false)
                        populaçãoInicial[i].setFitness(populaçãoInicial[i].getFitness()+(populaçãoInicial[i].getFitness()*populaçãoInicial[i].getDiferença()));
                }
        }
    }
        
        
        
        public boolean perturbação(int perturbado)
        {
            //int fitnessPerturbado = populaçãoInicial[perturbado].getFitness();
            double distânciaPerturbado = populaçãoInicial[perturbado].getDistância();
            Individuo aux = populaçãoInicial[perturbado];

            
            for(int i=0; i<15; i++)
            {
                int modoPerturbação = geradorAleatório.nextInt(3);
                switch(modoPerturbação)
                { 
                    case 0 :  
                        aux.cromossomo[0] = randLeite();  
                    break;  
   
                    case 1 :  
                        aux.cromossomo[1] = randAçúcar();  
                    break;  
   
                    case 2 :  
                    {
                        aux.cromossomo[0] = randLeite();
                        aux.cromossomo[1] = randAçúcar();
                    }
                    break;  
                 }
                 
                 aux.calculaFitness();
                 aux.calculaDistância();
                 
                 if (aux.getDistância() < distânciaPerturbado)
                    {
                        System.out.println("Perturbou\n");
                        populaçãoInicial[perturbado] = aux;
                        return true;
                    }
                    
                 i++;   

            }
        
        System.out.println("Não perturbou\n");
        return false;
        
        //sorteia alguns individuos ruins e tenta perturbá-los para tentar melhorá-los
        
        
        //reordena a população
    }
   
    
    public int randLeite()
    {
        return (100 + (geradorAleatório.nextInt(50) * 10));
    }
    
    public int randAçúcar()
    {
        return (30 + (geradorAleatório.nextInt(10) * 5));
    }
    
    public double randManteiga()
    {
        return (2 + geradorAleatório.nextInt(12) + geradorAleatório.nextDouble());
    }
    public double randAmido()
    {
        return (geradorAleatório.nextInt(2) +  + geradorAleatório.nextDouble());
    }
    public int randTempo1()
    {
        return (15 + (geradorAleatório.nextInt(5)));
    }
    public int randTempo2()
    {
        return (5 + (geradorAleatório.nextInt(5)));
    }
}
