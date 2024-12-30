package com.demoqa.utils.enums;

public enum Properties {
    INNER_TEXT {
        @Override
        public String toString() {
            return "innerText";
        }
    },

    NATURAL_HEIGHT {
        @Override
        public String toString() {
            return "naturalHeight";
        }
    },
    ID {
        @Override
        public String toString() {
            return "id";
        }
    },
    CLASS_NAME {
        @Override
        public String toString() {
            return "className";
        }
    },
    CSS_STYLE {
        @Override
        public String toString() {
            return "style";
        }
    },
    HREF {
        @Override
        public String toString() {
            return "href";
        }
    },
    SRC {
        @Override
        public String toString() {
            return "src";
        }
    },
    TITLE {
        @Override
        public String toString() {
            return "title";
        }
    },
    ALT {
        @Override
        public String toString() {
            return "alt";
        }
    },
    WIDTH {
        @Override
        public String toString() {
            return "width";
        }
    },
    HEIGHT {
        @Override
        public String toString() {
            return "height";
        }
    },
    VALUE {
        @Override
        public String toString() {
            return "value";
        }
    },
    TEXT_CONTENT {
        @Override
        public String toString() {
            return "textContent";
        }
    },
    DOWNLOAD {
        @Override
        public String toString() {
            return "download";
        }
    },
    OUTER_TEXT {
        @Override
        public String toString() {
            return "outerText";
        }
    },
    DEFAULT_VALUE {
        @Override
        public String toString() {
            return "defaultValue";
        }
    },
    ARIA_SELECTED {
        @Override
        public String toString() {
            return "ariaSelected";
        }
    },
    ARIA_DISABLED {
        @Override
        public String toString() {
            return "ariaDisabled";
        }
    },
    DISABLED {
        @Override
        public String toString() {
            return "disabled";
        }
    },
    CHECKED {
        @Override
        public String toString() {
            return "checked";
        }
    };
}