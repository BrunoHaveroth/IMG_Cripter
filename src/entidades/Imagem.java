/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author mizereke
 */
public class Imagem {
    private String nome;
    private String path;
    private String chave;
    private String status;
    private Boolean salvo;
    private Boolean pronto;

    public Imagem() {
    }

    public Imagem(String nome, String path, String chave, String status) {
        this.nome = nome;
        this.path = path;
        this.chave = chave;
        this.status = status;
        this.salvo = false;
        this.pronto = false;
    }

    public Boolean getPronto() {
        return pronto;
    }

    public void setPronto(Boolean pronto) {
        this.pronto = pronto;
    }
    

    public Boolean getSalvo() {
        return salvo;
    }

    public void setSalvo(Boolean salvo) {
        this.salvo = salvo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
