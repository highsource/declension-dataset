package org.hisrc.declension.dto.builder;

import java.util.function.Predicate;

import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalNumber;

public enum InflectionRule implements Predicate<InflectionBuilder> {

	NO_WORD_FORMS {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return inflection.getWordForms().isEmpty();
		}
	},
	SINGULAR_1 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// !Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) == null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// !Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) == null &&
			// !Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) == null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_AND_PLURAL_1 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// !Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) == null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// !Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) == null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_AND_PLURAL_1_2 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// !Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) == null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_AND_PLURAL_1_2_3 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// !Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) == null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) != null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_AND_PLURAL_1_2_3_4 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// !Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) == null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) != null &&
			// Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) != null;
		}
	},
	SINGULAR_1_2_AND_PLURAL_1 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// !Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) == null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_2_AND_PLURAL_1_2 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_2_AND_PLURAL_1_2_3 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) != null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_2_AND_PLURAL_1_2_3_4 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) != null &&
			// Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) != null;
		}
	},
	SINGULAR_1_2_3_AND_PLURAL_1 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) != null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// !Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) == null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_2_3_AND_PLURAL_1_2 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) != null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_2_3_AND_PLURAL_1_2_3 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) != null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) != null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	SINGULAR_1_2_3_4_AND_PLURAL_1_2 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) != null &&
			// Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) != null &&
			// Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) != null &&
			// Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) != null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) != null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	PLURAL_1 {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return
			// !Singular 1
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1) == null &&
			// !Singular 2
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2) == null &&
			// !Singular 3
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3) == null &&
			// !Singular 4
			inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4) == null &&
			// Plural 1
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1) != null &&
			// !Plural 2
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2) == null &&
			// !Plural 3
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3) == null &&
			// !Plural 4
			inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4) == null;
		}
	},
	REST {
		@Override
		public boolean test(InflectionBuilder inflection) {
			return true;
		}
	};

	@Override
	public abstract boolean test(InflectionBuilder inflection);

}
