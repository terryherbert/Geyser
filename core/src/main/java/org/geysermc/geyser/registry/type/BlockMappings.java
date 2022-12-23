/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.registry.type;

import lombok.Builder;
import lombok.Value;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.defintions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.defintions.SimpleBlockDefinition;
import org.cloudburstmc.protocol.common.DefinitionRegistry;

import java.util.Map;
import java.util.Set;

@Builder
@Value
public class BlockMappings {
    BlockDefinition bedrockAir;
    BlockDefinition bedrockWater;
    BlockDefinition bedrockMovingBlock;

    int blockStateVersion;

    BlockDefinition[] javaToBedrockBlocks;
    DefinitionRegistry<BlockDefinition> definitionRegistry;

    NbtList<NbtMap> bedrockBlockPalette;

    BlockDefinition commandBlock;

    Map<NbtMap, BlockDefinition> itemFrames;
    Map<String, NbtMap> flowerPotBlocks;

    Set<BlockDefinition> jigsawStates;

    public int getBedrockBlockId(int state) {
        if (state < 0 || state >= this.javaToBedrockBlocks.length) {
            return bedrockAir.getRuntimeId();
        }
        return this.javaToBedrockBlocks[state].getRuntimeId();
    }

    public BlockDefinition getBedrockBlock(int state) {
        if (state < 0 || state >= this.javaToBedrockBlocks.length) {
            return bedrockAir;
        }
        return this.javaToBedrockBlocks[state];
    }

    public BlockDefinition getItemFrame(NbtMap tag) {
        return this.itemFrames.get(tag);
    }

    public boolean isItemFrame(BlockDefinition definition) {
        if (definition instanceof SimpleBlockDefinition def) {
            return this.itemFrames.containsKey(def.getState());
        }

        return false;
    }
}