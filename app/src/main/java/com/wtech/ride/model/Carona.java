package com.wtech.ride.model;

import java.io.Serializable;

/**
 * Created by weldis on 25/11/16.
 */
public class Carona implements Serializable {

    private int idCarona;
    private int idDestino;
    private int idOrigem;
    private int idCondutor;
    private String horaDaPartida;
    private String listaDePassageiros[];
    private int numeroDeVagas;
    private String pontosDeEmbarque[];

    public Carona() {
        this.idOrigem = 0;
    }

    public int getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(int idOrigem) {
        this.idOrigem = idOrigem;
    }

    public int getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    public int getIdCarona() {
        return idCarona;
    }

    public void setIdCarona(int idCarona) {
        this.idCarona = idCarona;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getHoraDaPartida() {
        return horaDaPartida;
    }

    public void setHoraDaPartida(String horaDaPartida) {
        this.horaDaPartida = horaDaPartida;
    }

    public String[] getListaDePassageiros() {
        return listaDePassageiros;
    }

    public void setListaDePassageiros(String[] listaDePassageiros) {
        this.listaDePassageiros = listaDePassageiros;
    }

    public int getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(int numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }

    public String[] getPontosDeEmbarque() {
        return pontosDeEmbarque;
    }

    public void setPontosDeEmbarque(String[] pontosDeEmbarque) {
        this.pontosDeEmbarque = pontosDeEmbarque;
    }
}
