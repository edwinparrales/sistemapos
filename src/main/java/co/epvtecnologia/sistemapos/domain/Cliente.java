package co.epvtecnologia.sistemapos.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long idCliente;
      @Column(nullable = false)
      private String nombres;
      @Column(nullable = false,unique = true)
      private String numDocumento;
      @Column(nullable = false)
      private String tipoDocumento;
      @Column(nullable = false)
      private String direccion;
      @Column(nullable = false)
      private String telefono;

      @Column(nullable = true)
      private String email;

      public Cliente() {
      }

      public Long getIdCliente() {
            return idCliente;
      }

      public void setIdCliente(Long idCliente) {
            this.idCliente = idCliente;
      }

      public String getNombres() {
            return nombres;
      }

      public void setNombres(String nombres) {
            this.nombres = nombres;
      }

      public String getNumDocumento() {
            return numDocumento;
      }

      public void setNumDocumento(String numDocumento) {
            this.numDocumento = numDocumento;
      }

      public String getTipoDocumento() {
            return tipoDocumento;
      }

      public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
      }

      public String getDireccion() {
            return direccion;
      }

      public void setDireccion(String direccion) {
            this.direccion = direccion;
      }

      public String getTelefono() {
            return telefono;
      }

      public void setTelefono(String telefono) {
            this.telefono = telefono;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }
}
