package br.calebe.ticketmachine.core;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class PapelMoeda {

    protected int valor;  //SUGESTÃO: documentar e padronizar "centavos" para evitar ambiguidade.
    protected int quantidade; // PROBLEMA: em CSU01, a inserção é de UMA nota por vez.

    public PapelMoeda(int valor, int quantidade) {
        this.valor = valor;  //// PROBLEMA: não há validação da denominação nem da quantidade. validar `quantidade >= 1`; em contexto de inserção (CSU01)   
        this.quantidade = quantidade;
    }

    public int getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

