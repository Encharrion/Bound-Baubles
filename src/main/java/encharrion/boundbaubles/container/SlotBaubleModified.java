package encharrion.boundbaubles.container;

import java.lang.reflect.Field;

import baubles.api.cap.IBaublesItemHandler;
import baubles.common.container.SlotBauble;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;

public class SlotBaubleModified extends SlotBauble {

	private SlotBaubleModified(EntityPlayer player, IBaublesItemHandler itemHandler, int slot, int par4, int par5) {
		super(player, itemHandler, slot, par4, par5);
	}

	/**
	 * Takes an existing bauble slot and pulls its properties to make a new modified version of that bauble slot.
	 * 
	 * @param baubleSlot
	 * @return The modified bauble slot
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static SlotBaubleModified modifyBaubleSlot(SlotBauble baubleSlot) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Class<?> c = Class.forName("baubles.common.container.SlotBauble");
		
		Field playerField = c.getDeclaredField("player");
		playerField.setAccessible(true);
		EntityPlayer player = (EntityPlayer)playerField.get(baubleSlot);
		
		Field slotField = c.getDeclaredField("baubleSlot");
		slotField.setAccessible(true);
		int slot = slotField.getInt(baubleSlot);
		
		IBaublesItemHandler itemHandler = (IBaublesItemHandler)baubleSlot.getItemHandler();
		
		return new SlotBaubleModified(player, itemHandler, slot, baubleSlot.xPos, baubleSlot.yPos);
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return super.canTakeStack(player) && !EnchantmentHelper.hasBindingCurse(getStack());
	}

}
