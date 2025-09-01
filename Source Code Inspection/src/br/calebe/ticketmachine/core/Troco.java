package br.calebe.ticketmachine.core;

import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
class Troco {

    protected PapelMoeda[] papeisMoeda;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[6];
        int count = 0;
        while (valor % 100 != 0) {   // BUG CRÍTICO: laço infinito se (valor % 100 != 0) pois "valor" NUNCA muda dentro do while.
            count++;
        }
        //OK: 100 na posição [5] (se a convenção for ordem decrescente).
        papeisMoeda[5] = new PapelMoeda(100, count);
        count = 0;
        // BUG CRÍTICO: mesma lógica incorreta de laço infinito (valor não é reduzido).
        while (valor % 50 != 0) {
            count++;
        }
        papeisMoeda[4] = new PapelMoeda(50, count);
        count = 0;
        //mesmo bug, looping infinito
        while (valor % 20 != 0) {
            count++;
        }
        papeisMoeda[3] = new PapelMoeda(20, count);
        count = 0;
        //mesmo bug, looping infinito
        while (valor % 10 != 0) {
            count++;
        }
        papeisMoeda[2] = new PapelMoeda(10, count);
        count = 0;
        //mesmo bug, looping infinito
        while (valor % 5 != 0) {
            count++;
        }
        papeisMoeda[1] = new PapelMoeda(5, count);
        count = 0;
        //mesmo bug, looping infinito
        while (valor % 2 != 0) {
            count++;
        }
        // BUG DE ÍNDICE: aqui deveria ser [0], mas está sobrescrevendo [1] (perdendo as notas de 5).
        papeisMoeda[1] = new PapelMoeda(2, count);
    }

    public Iterator<PapelMoeda> getIterator() {
        return new TrocoIterator(this);
    }

    class TrocoIterator implements Iterator<PapelMoeda> {

        protected Troco troco;

        public TrocoIterator(Troco troco) {
            this.troco = troco;
        }

        @Override
        public boolean hasNext() {
            // BUG DE FAIXA: começa em 6, mas o último índice válido é 5 → ArrayIndexOutOfBoundsException.
            for (int i = 6; i >= 0; i++) {
                if (troco.papeisMoeda[i] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PapelMoeda next() {
            PapelMoeda ret = null;
            // BUG DE FAIXA: começa em 6, mas o último índice válido é 5 → ArrayIndexOutOfBoundsException.
            for (int i = 6; i >= 0 && ret != null; i++) {
                if (troco.papeisMoeda[i] != null) {
                    ret = troco.papeisMoeda[i];
                    troco.papeisMoeda[i] = null;
                }
            }
            // BUG DE CONTRATO: se não houver próximo, deveria lançar NoSuchElementException, não retornar null.
            return ret;
        }

        @Override
        public void remove() {
            // BUG SEMÂNTICO
            next();
        }
    }
}

