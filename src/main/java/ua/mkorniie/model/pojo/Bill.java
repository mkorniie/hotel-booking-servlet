package ua.mkorniie.model.pojo;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import ua.mkorniie.controller.util.Rounder;

import java.util.Objects;

@NoArgsConstructor
public class Bill {
	private static final Logger			logger = Logger.getLogger(Bill.class);


	@Getter @Setter private int             	id;
	@Getter			private double				sum;
	@Getter @Setter private boolean             isPaid;
	@Getter 		private Request				request;
	@Getter 		private Room				room;

	public Bill(double sum, boolean isPaid, @NotNull Request request, @NotNull Room room) {
		setSum(sum);
		this.isPaid = isPaid;
		this.request = request;
		this.room = room;

		logger.info("Object Bill successfully created");
	}

	public Bill(int id, double sum, boolean isPaid, @NotNull Request request, @NotNull Room room) {
		if (id < 0) {
			throw new IllegalArgumentException("Id can't be less than zero: id value is " + id);
		}
		this.id = id;
		setSum(sum);
		this.isPaid = isPaid;
		this.request = request;
		this.room = room;

		logger.info("Object Bill successfully created");
	}

	public void setSum(double sum) {
			this.sum = Rounder.round(sum);
			logger.info("Double value of 'sum' field set succesfully: " + this.sum);
	}

	public void setRequest(@NotNull Request request) {
		this.request = request;
	}

	public void setRoom(@NotNull Room room) {
		this.room = room;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bill bill = (Bill) o;
		return id == bill.id &&
				Double.compare(bill.sum, sum) == 0 &&
				isPaid == bill.isPaid &&
				request.equals(bill.request) &&
				room.equals(bill.room);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sum, isPaid, request, room);
	}
}