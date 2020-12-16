package it.univaq.incipict.ems.monitor.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class Instance implements Comparable<Instance> {
	private String id;

	private Date startTime;

	private Long earthquakeDuration;

	private Long totalDuration;

	@Override
	public int compareTo(Instance o) {
		if (this.startTime != null && o != null && o.startTime != null ) {
			return -this.startTime.compareTo(o.startTime);			
		} else {
			return 0;
		}
	}
}
