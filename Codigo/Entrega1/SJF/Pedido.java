public class Pedido {
    private String nome;
    private int prazo;
    private int nprodutos;
    private int prioridade;
    private double tempo;
    private boolean atendimento = false;
    private boolean prazoCumprido = false;
    public Pedido(String str){
        String campos[] = str.split(";");
        this.nome = campos[0];
        this.nprodutos = Integer.parseInt(campos[1]);
        this.prazo = Integer.parseInt(campos[2]);
    }
    @Override
    public String toString() {
        String att = "não foi atendido";
        if(this.atendimento){
            att = "atendido";
        }
        
        return "nome: " + nome + ", nprodutos: " + nprodutos + ", prazo: " + prazo + ", prioridade: "+prioridade+", tempo de finalização(min): "+tempo+", Atendimento: "+att+", Prazo cumprido? "+this.cumpriuPrazo()+ "]";
    }

    public String getNome() {
        return nome;
    }

    public int getPrazo() {
        return prazo;
    }

    public int getNprodutos() {
        return nprodutos;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public double getTempo() {
        return tempo;
    }
    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
    public void setAtendimento(boolean att){
        this.atendimento = att;
    }
    public boolean isPrazoCumprido() {
        return prazoCumprido;
    }
    public void setPrazoCumprido() {
        if(this.prazo !=0 && this.prazo >= this.tempo){
            this.prazoCumprido = true;
        }
    }
    public String cumpriuPrazo(){
        this.setPrazoCumprido();
        String pc = "não";
        if(this.isPrazoCumprido()){
            pc = "sim";
        }
        else if(this.prazo == 0){
            pc = "não possui prazo";
        }
        return pc;
    }
    
    
    
}
