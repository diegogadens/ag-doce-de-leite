import java.util.Random;
import java.util.Arrays; 

public class Principal
{
    Random geradorAleat�rio;
    Individuo popula��oInicial[];
    Individuo popula��oSelecionada[];
    Individuo ordenador;
    Individuo ranking[];
    int gera��o = 0;
    public int tamanhoPopula��o;
    public double taxaCross;
    public double taxaMuta��o;
    public int numGera��es;
    
    
    public Principal(int tamanhoPopula��o, double taxaCross, double taxaMuta��o, int numGera��es)
    {
        
        this.tamanhoPopula��o = tamanhoPopula��o;
        this.taxaCross = taxaCross;
        this.taxaMuta��o = taxaMuta��o;
        this.numGera��es = numGera��es;
        
        geradorAleat�rio = new Random();
        popula��oInicial = new Individuo[tamanhoPopula��o];
        popula��oSelecionada = new Individuo[tamanhoPopula��o];
        ordenador = new Individuo(0,0,0,0,0,0);
        ranking = new Individuo[tamanhoPopula��o];
        
        
        
        geraPopula��oInicial();
        System.out.println("Gerou popula��o inicial");
        avaliaPopula��o();
        System.out.println("Avaliou Popula��o Inicial");
              
        
        System.out.println("vai ordenar...");
        ordena();
        System.out.println("Ordenou");
        
        gera��o++;
       // System.out.println("teste da ordena��o");
       // for(int i=0; i<tamanhoPopula��o; i++)
       // {
       //     System.out.println(i+"�: "+ popula��oInicial[i].getDist�ncia());
       // }
           
         imprime();   
       
       while (gera��o < numGera��es)
      
        {
       //    try 
       //    {
       //        Thread.sleep(3000);
       //    } 
       //    catch (InterruptedException ex) 
       //    {} 
           System.out.println("Vai gerar a nova Popula��o");
           pr�ximaGera��o();
           System.out.println("Gerou a nova popula��o");
           
           //for(int i=1; i<tamanhoPopula��o; i++)
           //{
           // if ( popula��oInicial[i].getFitness() == popula��oInicial[i-1].getFitness())
           // {
           //     popula��oInicial[i] = new Individuo(randLeite(), randA��car(), randManteiga(), randAmido(), randTempo1(), randTempo2());
                //popula��oInicial[i-1] = new Individuo(0, 0, 0, 0, 0, 0);
           //    popula��oInicial[i].calculaFitness();
           //  }
           //}
           
           System.out.println("Vai avaliar a nova popula��o");
           avaliaPopula��o();
           System.out.println("Avaliou a nova popula��o");
           System.out.println(gera��o+"� gera��o");
           System.out.println("Vai ordenar a nova popula��o");
           ordena();
           System.out.println("Ordenou a nova popula��o");
           System.out.println("\n\n");
           
           System.out.println("Busca Local");
           buscaLocal();
           System.out.println("\n\n");
           
           System.out.println("Vai ordenar a nova popula��o");
           ordena();
           System.out.println("Ordenou a nova popula��o");
           System.out.println("\n\n");
           
           //for(int i=0; i<tamanhoPopula��o; i++)
           //{
           // System.out.println(i+"�: "+ popula��oInicial[i].getDist�ncia());
           //}
           gera��o++;
           imprime();
           
        
           

       }
    }


    public void geraPopula��oInicial()
    {
        for(int i=0; i<tamanhoPopula��o; i++)
        {
            popula��oInicial[i] = new Individuo(randLeite(), randA��car(), randManteiga(), randAmido(), randTempo1(), randTempo2());
            popula��oInicial[i].setDiferen�a((popula��oInicial[i].cromossomo[1] + popula��oInicial[i].cromossomo[2] + popula��oInicial[i].cromossomo[3])/popula��oInicial[i].cromossomo[0]);
            
            //ranking[i] = popula��oInicial[i].getFitness();
        }
    }
    
    
    public void pr�ximaGera��o()
    {
        System.out.println("Inicia nova Popula��o.");
        for(int i=0; i<(tamanhoPopula��o*0.3); i++)
        {
            /*Pega os 30% melhores*/
            popula��oSelecionada[i] = popula��oInicial[i];
        } 
        
        for(int i=(int)(tamanhoPopula��o*0.3); i<(tamanhoPopula��o*0.45); i++)
        {
            /*Pega os 15% piores*/
            popula��oSelecionada[i] = popula��oInicial[i];
        }    
        
        for(int i=(int)(tamanhoPopula��o*0.45); i<(tamanhoPopula��o*(0.45+taxaCross)); i++)
        {
            /*Pega 20 gerados por cross da pop Inicial*/
            //popula��oSelecionada[i] = crossover();
            crossover(i);
            //popula��oSelecionada[i] = new Individuo(randLeite(), randA��car(), randManteiga(), randAmido(), randTempo1(), randTempo2());
        }
        
        for(int i=(int)(tamanhoPopula��o*(0.45+taxaCross)); i<(tamanhoPopula��o*(0.45+taxaCross+taxaMuta��o)); i++)
        {
            /*pega 2 individuos gerados por muta��o da Pop Inicial*/
            muta��o(i);
        }
        
        for(int i=(int)(tamanhoPopula��o*(0.45+taxaCross+taxaMuta��o)); i<tamanhoPopula��o; i++)
        {
            popula��oSelecionada[i] = new Individuo(randLeite(), randA��car(), randManteiga(), randAmido(), randTempo1(), randTempo2());
        }
       
       // System.out.println("popula��oInicial = popula��oSelecionada.");
        
        
        popula��oInicial = popula��oSelecionada;

        
        
        //System.out.println("Come�a Ordena��o nova popula��o");
        
       // System.out.println("Terminou a nova popula��o");   
  
        //for(int i=0; i<tamanhoPopula��o; i++)
        //{
            //popula��oInicial[i].getFitness() = popula��oSelecionada[i].getFitness();
            //System.out.println("vai ordenar na " + gera��o + " gera��o");
            //Arrays.sort(ranking);
            //System.out.println(popula��oSelecionada[i].getFitness());
       // }
    }    
    
    //public Individuo crossover()
    public void crossover(int z)
    {
        int pai_1, pai_2, ponto;
        Individuo individuoAux = new Individuo(0, 0, 0, 0, 0, 0);
        
        pai_1 = geradorAleat�rio.nextInt(tamanhoPopula��o/2);
        //pai_2 = (tamanhoPopula��o/2 + geradorAleat�rio.nextInt(tamanhoPopula��o/2));
        pai_2 = (geradorAleat�rio.nextInt(tamanhoPopula��o));        
        
        System.out.println("Pai_1: "+pai_1);
        System.out.println("Fitness: "+popula��oInicial[pai_1].getFitness()+"\n");
        popula��oInicial[pai_1].imprimeDados();

        System.out.println("Pai_2: "+pai_2);
        System.out.println("Fitness: "+popula��oInicial[pai_2].getFitness()+"\n");
        popula��oInicial[pai_2].imprimeDados();
       
        
        ponto = (1 + geradorAleat�rio.nextInt(5));
        
        for(int i = 0; i<ponto; i++)
        {
            individuoAux.cromossomo[i] = popula��oInicial[pai_1].cromossomo[i];
        }
        
        for(int i = ponto; i<6; i++)
        {
            individuoAux.cromossomo[i] = popula��oInicial[pai_2].cromossomo[i];
        }

        individuoAux.calculaFitness();
        
        System.out.println("Depois do cross: ");
        System.out.println("Fitness: "+individuoAux.getFitness()+"\n");
        individuoAux.imprimeDados();
        
        popula��oSelecionada[z] = individuoAux;
        
    }
    
   
   public void muta��o(int z)
   {
        int individuo, gene;
        individuo = geradorAleat�rio.nextInt(tamanhoPopula��o);
        gene = geradorAleat�rio.nextInt(6);
        //Individuo individuoAux = popula��oInicial[individuo];
        
        
        System.out.println("Individuo Mutado: "+individuo);
        System.out.println("Fitness antes: "+popula��oInicial[individuo].getFitness());
        //popula��oInicial[individuo].imprimeDados();
        
        System.out.println("Gene: "+gene+" Antes da muta��o: " + popula��oInicial[individuo].cromossomo[gene]+"\n");
        switch(gene)
        { 
            case 0 :  
                popula��oInicial[individuo].cromossomo[gene] = randLeite();  
            break;  
   
            case 1 :  
                popula��oInicial[individuo].cromossomo[gene] = randA��car();  
            break;  
   
            case 2 :  
                popula��oInicial[individuo].cromossomo[gene] = randManteiga();  
            break;  
   
            case 3 :  
                popula��oInicial[individuo].cromossomo[gene] = randAmido();  
            break;  
            
            case 4 :  
                popula��oInicial[individuo].cromossomo[gene] = randTempo1();  
            break;  
   
            case 5 :  
                popula��oInicial[individuo].cromossomo[gene] = randTempo2();  
            break;  
        }
        popula��oInicial[individuo].calculaFitness();
        //System.out.print("Individuo Mutado: "+individuo+" Depois da muta��o: ");
        System.out.println("Fitness depois: "+popula��oInicial[individuo].getFitness());
        //popula��oInicial[individuo].imprimeDados();
        System.out.println("Gene: "+gene+" Depois da muta��o: " + popula��oInicial[individuo].cromossomo[gene]+"\n");
     
        popula��oSelecionada[z] = popula��oInicial[individuo];
   }
    
    
    public void ordena()
    {
       for(int i=0; i<tamanhoPopula��o; i++)
         {
            for(int j=0; j<tamanhoPopula��o-1; j++)
            {
                if(popula��oInicial[j].getDist�ncia() > popula��oInicial[j+1].getDist�ncia())
                {
                   ordenador = popula��oInicial[j];
                   popula��oInicial[j] = popula��oInicial[j+1];
                   popula��oInicial[j+1] = ordenador;
                }
            }
        } 
   }

   
   public void imprime()
   {
       System.out.println(gera��o + "� Gera��o \n");
           try 
           {
               Thread.sleep(2);
           } 
           catch (InterruptedException ex) 
           {}
       for(int i=0; i<tamanhoPopula��o; i++)
       {
           if((popula��oInicial[i].getFitness() > 597) && (popula��oInicial[i].getFitness() < 603))
           {
               System.out.println();
               System.out.printf("Fitness: %.4f \n", popula��oInicial[i].getFitness());
               popula��oInicial[i].imprimeDados();

           }
       }
    }
    
    
    public void avaliaPopula��o()
    {
        for(int i=0; i<tamanhoPopula��o; i++)
        {
            popula��oInicial[i].calculaFitness();
            popula��oInicial[i].calculaDist�ncia();
            popula��oInicial[i].setDiferen�a((popula��oInicial[i].cromossomo[1] + popula��oInicial[i].cromossomo[2] + popula��oInicial[i].cromossomo[3])/popula��oInicial[i].cromossomo[0]);
            //poula��oSelecionada[i] = popula��oInicial[i];
        }
    }
    
    
    public void buscaLocal()
    {
        // verifica os individuos com fitness bom por�m com receitas n�o-ideais
        for(int i=0; i<tamanhoPopula��o; i++)
        {
            popula��oInicial[i].setDiferen�a((popula��oInicial[i].cromossomo[1] + popula��oInicial[i].cromossomo[2] + popula��oInicial[i].cromossomo[3])/popula��oInicial[i].cromossomo[0]);
            //System.out.println("diferen�a do "+ i +": "+popula��oInicial[i].getDiferen�a());
            //System.out.println(popula��oInicial[i].cromossomo[1] +" + "+ popula��oInicial[i].cromossomo[2] +" + "+ popula��oInicial[i].cromossomo[3]+"  "+popula��oInicial[i].cromossomo[0]);
            //System.out.println("Diferen�a: " + popula��oInicial[i].getDiferen�a());
            
            
            
            
            
            
                if(popula��oInicial[i].getDiferen�a() < 0.15 && popula��oInicial[i].getDist�ncia() <= 50)
                {
                    //tenta perturb�-los 3 vezes, se a perturba��o funcionar Ok, sen�o aplica uma penalidade e o faz cair no ranking
                    System.out.println("Tenta perturbar o " + i);
                    if(perturba��o(i) == false)
                        popula��oInicial[i].setFitness(popula��oInicial[i].getFitness()-(popula��oInicial[i].getFitness()*popula��oInicial[i].getDiferen�a()));
                        
                }
                else
                if(popula��oInicial[i].getDiferen�a() > 0.25  && popula��oInicial[i].getDist�ncia() <= 50)
                {
                    System.out.println("Tenta perturbar o " + i);
                    if(perturba��o(i) == false)
                        popula��oInicial[i].setFitness(popula��oInicial[i].getFitness()+(popula��oInicial[i].getFitness()*popula��oInicial[i].getDiferen�a()));
                }
        }
    }
        
        
        
        public boolean perturba��o(int perturbado)
        {
            //int fitnessPerturbado = popula��oInicial[perturbado].getFitness();
            double dist�nciaPerturbado = popula��oInicial[perturbado].getDist�ncia();
            Individuo aux = popula��oInicial[perturbado];

            
            for(int i=0; i<15; i++)
            {
                int modoPerturba��o = geradorAleat�rio.nextInt(3);
                switch(modoPerturba��o)
                { 
                    case 0 :  
                        aux.cromossomo[0] = randLeite();  
                    break;  
   
                    case 1 :  
                        aux.cromossomo[1] = randA��car();  
                    break;  
   
                    case 2 :  
                    {
                        aux.cromossomo[0] = randLeite();
                        aux.cromossomo[1] = randA��car();
                    }
                    break;  
                 }
                 
                 aux.calculaFitness();
                 aux.calculaDist�ncia();
                 
                 if (aux.getDist�ncia() < dist�nciaPerturbado)
                    {
                        System.out.println("Perturbou\n");
                        popula��oInicial[perturbado] = aux;
                        return true;
                    }
                    
                 i++;   

            }
        
        System.out.println("N�o perturbou\n");
        return false;
        
        //sorteia alguns individuos ruins e tenta perturb�-los para tentar melhor�-los
        
        
        //reordena a popula��o
    }
   
    
    public int randLeite()
    {
        return (100 + (geradorAleat�rio.nextInt(50) * 10));
    }
    
    public int randA��car()
    {
        return (30 + (geradorAleat�rio.nextInt(10) * 5));
    }
    
    public double randManteiga()
    {
        return (2 + geradorAleat�rio.nextInt(12) + geradorAleat�rio.nextDouble());
    }
    public double randAmido()
    {
        return (geradorAleat�rio.nextInt(2) +  + geradorAleat�rio.nextDouble());
    }
    public int randTempo1()
    {
        return (15 + (geradorAleat�rio.nextInt(5)));
    }
    public int randTempo2()
    {
        return (5 + (geradorAleat�rio.nextInt(5)));
    }
}
