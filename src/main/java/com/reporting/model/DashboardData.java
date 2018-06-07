package com.reporting.model;

public class DashboardData {

//	select districts,mtc_name,tabd,total_admissions,round((NULLIF(t_exit,0)*100)/total_admissions, 2) as d_rate,t_exit,
//	recovery_r,bor,als,defaulter_r,death_r,awg from final_report;
	
	private String district_name;
	private String mtc_name ;
	private Integer tabd;
	private Integer totalAdmissions;
	private Double dischargeRate;
	private Integer totalExit;
	private Double recoveryR;
	private Double als;
	private Double bor;
	private Double defaulterR;
	private Double deathR;
	private Double awg;
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getMtc_name() {
		return mtc_name;
	}
	public void setMtc_name(String mtc_name) {
		this.mtc_name = mtc_name;
	}
	public Integer getTabd() {
		return tabd;
	}
	public void setTabd(Integer tabd) {
		this.tabd = tabd;
	}
	public Integer getTotalAdmissions() {
		return totalAdmissions;
	}
	public void setTotalAdmissions(Integer totalAdmissions) {
		this.totalAdmissions = totalAdmissions;
	}
	public Double getDischargeRate() {
		return dischargeRate;
	}
	public void setDischargeRate(Double dischargeRate) {
		this.dischargeRate = dischargeRate;
	}
	public Integer getTotalExit() {
		return totalExit;
	}
	public void setTotalExit(Integer totalExit) {
		this.totalExit = totalExit;
	}
	public Double getRecoveryR() {
		return recoveryR;
	}
	public void setRecoveryR(Double recoveryR) {
		this.recoveryR = recoveryR;
	}
	public Double getAls() {
		return als;
	}
	public void setAls(Double als) {
		this.als = als;
	}
	public Double getBor() {
		return bor;
	}
	public void setBor(Double bor) {
		this.bor = bor;
	}
	public Double getDefaulterR() {
		return defaulterR;
	}
	public void setDefaulterR(Double defaulterR) {
		this.defaulterR = defaulterR;
	}
	public Double getDeathR() {
		return deathR;
	}
	public void setDeathR(Double deathR) {
		this.deathR = deathR;
	}
	public Double getAwg() {
		return awg;
	}
	public void setAwg(Double awg) {
		this.awg = awg;
	}
	
}
