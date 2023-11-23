public class Consulta {
    private String codigo, uf;
    public Consulta (Integer codigo, String uf)throws Exception{
        if(codigo==null) throw new Exception("Código não informado.");
        if(uf==null) throw new Exception("Estado não informado.");
        this.codigo=String.valueOf(codigo);
        this.uf=uf;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUf() {
        return uf;
    }

}
