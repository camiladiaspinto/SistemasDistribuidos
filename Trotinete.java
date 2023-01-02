


public class Trotinete{
    private String numtrotinete;
    private String numreserva;
    private int estado;

    private coordenadas coord;


    public Trotinete(String numtrotinete, int x, int y ,String numreserva, int estado){
        this.numtrotinete = numtrotinete;
        this.numreserva = numreserva;
        this.estado = estado;
        coordenadas c = new coordenadas(x,y);
        this.coord=c;
    }

    public String getNumtrotinete() {
        return numtrotinete;
    }

    public void setNumtrotinete(String numtrotinete) {
        this.numtrotinete = numtrotinete;
    }

    public void setNumreserva(String numreserva) {
        this.numreserva = numreserva;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNumreserva() {
        return numreserva;
    }

    public int getEstado() {
        return estado;
    }
    public coordenadas getLocation(){
        return this.coord;
    }

}


//Pensamento para a funcionalidade de LISTAR A TROTINETES
//o utilizador introduz as coordenadas da sua lozalização atual, a apartir disso calculamos a distância de manhattan. A partir da distancia, listamos os lugares todos em que há
 //trotinetes e selecionamos as trotinetes mais perto. 

 //Pensamento para a funcionalidade de RESERVAR AS TROTINERES
 //o utilizador seleciona a trotinete que pretende, se a trotinete estiver livre para utilização, o servidor notifica o cliente com um código de reserva(random?) e 
 //as coordenadas da trotinete. Caso a trotinete não esteja livre, o servidor notifica o cliente cm mensagem de insucesso. 
 //o utilizador também pode optar por esperar por uma trotinete livre na sua área, mas os pedidos têm de ser tratados de forma sequencial 

 //Pensamento para a funcionalidade de ESTACIONAR A TROTINETE
 //Definir uma função que calcule o custo da viagem consoante o tempo e os kilometros. Cliente volta ao menu e opta por estacionar, e insere o seu código de reserva. 
 //Servidor verifica código de reserva e envia o custo da viagem. Cliente tem de inserir as coordenadas de onde deixou a trotinete, que depois vão para a lista das
 //trotinetes