/*
 * MIT License
 *
 * Copyright (c) 2023 Demeng Chen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.demeng.commandbuttons.util;

import dev.demeng.pluginbase.Common;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * General utilities.
 */
public class Utils {

  /**
   * Checks if the provided block is air.
   *
   * @param block The block to check
   * @return True if air, false otherwise
   */
  public static boolean isAir(Block block) {
    return block == null || block.getType() == Material.AIR
        || (Common.isServerVersionAtLeast(13) && block.getType().isAir());
  }

  /**
   * Gets the Location of the BlockFace of the block the player is currently targeting.
   *
   * @param player the player whose targeted blocks BlockFace is to be checked.
   * @return the Location of the BlockFace of the targeted block, or null if the targeted block is non-occluding.
   */
  public static Location getBlockFaceLocation(Player player) {
    List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
    if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
    Block targetBlock = lastTwoTargetBlocks.get(1);
    Block adjacentBlock = lastTwoTargetBlocks.get(0);

    return adjacentBlock.getLocation();
  }

  /**
   * Checks if the provided block has an item frame occupying it.
   *
   * @param location the location to be checked
   * @return true or false
   */
  public static Boolean isItemFrame(Location location) {
    for (Entity e : location.getChunk().getEntities()) {
      if (e instanceof ItemFrame
              && (e.getLocation().getBlockX() == location.getBlockX())
              && (e.getLocation().getBlockY() == location.getBlockY())
              && (e.getLocation().getBlockZ() == location.getBlockZ())) {
        return true;
      }
    }
    return false;
  }
}
