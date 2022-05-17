package cruzeiro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "Fatura")
public class FaturaBean {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull (message = "Informe o id da cabine reservada.")
	private String idCabine;
	
	@NotNull (message = "Informe a data de reserva.")
	private Date dataFatura;
	
	@NotNull (message = "Informe o valor total da reserva.")
	private Double total;
	
	
	public FaturaBean() {
		super();
	}
	
	public FaturaBean(int id, String idCabine, Date dataFatura, double total) {
		super();
		this.id = id;
		this.idCabine = idCabine;
		this.dataFatura = dataFatura;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdCabine() {
		return idCabine;
	}

	public void setIdCabine(String idCabine) {
		this.idCabine = idCabine;
	}

	public Date getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(Date dataFatura) {
		this.dataFatura = dataFatura;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
