package ir.mrmoshkel.common.infrastructure.idgenerator;

import ir.mrmoshkel.contract.IdGenerator;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator<UUID> {
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
