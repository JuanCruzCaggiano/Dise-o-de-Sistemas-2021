package dds.domain.seguridad.usuario;

import dds.domain.asociacion.Asociacion;
import dds.domain.seguridad.validador.ValidadorPassword;
import dds.servicios.HashHelper;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import java.security.*;

public class Usuario {
    private String idUsuario;
    private String userName;
    private String password;
    private LocalDateTime lastPasswordDT;
    private Integer intentosFallidos;
    private List<String> usedPasswords = new ArrayList<>();
    private Boolean isBlocked;
    private Asociacion asociacion;

    public Usuario (String userName, String password) throws NoSuchAlgorithmException{
        this.userName = userName;
        ValidadorPassword.getValidadorPassword().validarPassword(password,this);
        this.lastPasswordDT = LocalDateTime.now(ZoneOffset.UTC);
        this.isBlocked = false;
        this.intentosFallidos= 0;
        this.password = HashHelper.getHashHelper().passwordAMD5(password);
        this.usedPasswords.add(this.password);
        setLastPasswordDT(LocalDateTime.now(ZoneOffset.UTC));
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> lastPasswords() {
        int cant = 5;
        if (usedPasswords.size() < 5) {
            cant = usedPasswords.size();
        }
        return this.usedPasswords.subList(usedPasswords.size() - cant, usedPasswords.size());
    }

    public void changePassword(String newPassword) throws NoSuchAlgorithmException {
        ValidadorPassword.getValidadorPassword().validarPassword(newPassword, this);
        setPassword(HashHelper.getHashHelper().passwordAMD5(newPassword));
        addUsedPassword(HashHelper.getHashHelper().passwordAMD5(newPassword));
        setLastPasswordDT(LocalDateTime.now(ZoneOffset.UTC));
    }

    public Boolean passwordVencida(){
        return lastPasswordDT.isBefore(LocalDateTime.now(ZoneOffset.UTC).minusDays(30));
    }

    // Metodos de bloqueado
    public void bloquear() {
        isBlocked = true;
    }
    public void desbloquear() {
        isBlocked = false;
    }
    public boolean estaBloqueado() {
        return isBlocked;
    }


    public void addUsedPassword(String newPassword) {
        this.usedPasswords.add(newPassword);
    }

    public void setLastPasswordDT(LocalDateTime newLastPasswordDT){
        this.lastPasswordDT = newLastPasswordDT;
    }

    //Va a servir al momento del logueo
    public void verificarIntentosFallidos() {
        if (intentosFallidos == 3) {
            this.bloquear();
        }
    }
    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }
    public void sumaIntentoFallido() {
        this.intentosFallidos += 1;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String id) {
        this.idUsuario =id;
    }
}
