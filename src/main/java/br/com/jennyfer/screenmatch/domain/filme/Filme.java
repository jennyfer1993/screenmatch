package br.com.jennyfer.screenmatch.domain.filme;

import jakarta.persistence.*;

@Entity
@Table(name = "filmes") // indica o nome da tabela
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para gerar o ID automaticamente
    private Long  id;
    private String nome;
    private int duracaoEmMinutos;
    private int anoLancamento;
    private String genero;



    public Filme(DadosCadastroFilmes dados) { //construtor
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
        this.genero = dados.genero();
    }

    public Filme() {} // precisa criar construtor padrão do JAVA sem argumentos


    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", duracaoEmMinutos=" + duracaoEmMinutos +
                ", anoLancmento=" + anoLancamento +
                ", genero='" + genero + '\'' +
                '}';
    }

    public Long getId() {

        return id;
    }

    public String getNome() {

        return nome;
    }

    public int getDuracaoEmMinutos() {

        return duracaoEmMinutos;
    }

    public int getAnoLancmento() {

        return anoLancamento;
    }

    public String getGenero() {

        return genero;
    }

    public void atualizaDados(DadosAlteracaoFilmes dados) {
        this.nome = dados.nome();
        this.duracaoEmMinutos = dados.duracao();
        this.anoLancamento = dados.ano();
        this.genero = dados.genero();
    }
}
