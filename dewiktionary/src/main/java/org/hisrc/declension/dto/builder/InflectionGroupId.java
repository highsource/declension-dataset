package org.hisrc.declension.dto.builder;

import java.io.Serializable;
import java.util.Objects;

import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalNumber;

public class InflectionGroupId implements Serializable {

	private static final long serialVersionUID = 4373821949254309667L;
	private final GrammaticalNumber grammaticalNumber;
	private final int inflectionGroup;

	public InflectionGroupId(GrammaticalNumber grammaticalNumber, int inflectionGroup) {
		this.grammaticalNumber = grammaticalNumber;
		this.inflectionGroup = inflectionGroup;
	}

	public GrammaticalNumber getNumber() {
		return grammaticalNumber;
	}

	public int getInflectionGroup() {
		return inflectionGroup;
	}

	@Override
	public String toString() {
		return getNumber() + "[" + getInflectionGroup() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNumber(), getInflectionGroup());
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		InflectionGroupId that = (InflectionGroupId) object;
		return Objects.equals(this.getNumber(), that.getNumber())
				&& this.getInflectionGroup() == that.getInflectionGroup();
	}
}