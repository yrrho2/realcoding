package ac.cnu.realcoding.encoding;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Base62ProcessorTest {

    @Test
    public void basic_encode() {
        assertThat(Base62Processor.encode(0)).isEqualTo("A");
        assertThat(Base62Processor.encode(1234)).isEqualTo("T4");
        assertThat(Base62Processor.encode(11157)).isEqualTo("C37");
    }

    @Test
    public void basic_decode() {
        assertThat(Base62Processor.decode("A")).isEqualTo(0);
        assertThat(Base62Processor.decode("T4")).isEqualTo(1234);
        assertThat(Base62Processor.decode("2TX")).isEqualTo(208777);
        assertThat(Base62Processor.decode("jU")).isEqualTo(2190);
    }
}
