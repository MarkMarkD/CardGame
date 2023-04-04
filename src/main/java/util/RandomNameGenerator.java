package util;

import java.util.List;
import java.util.Random;

public class RandomNameGenerator implements NameProvider {

    private static final List<String> NAMES = List.of("Bob", "Pibody", "Serafim", "Bartolomew", "Leopold", "Donald");

    @Override
    public String provideName() {
        return NAMES.get(new Random().nextInt(NAMES.size()));
    }
}
