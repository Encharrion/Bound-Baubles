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

	public static SlotBaubleModified modifyBaubleSlot(SlotBauble baubleSlot) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		Class<?> c = Class.forName("baubles.common.container.SlotBauble");
		
		Field playerField = c.getDeclaredField("player");
		playerField.setAccessible(true);
		EntityPlayer player = (EntityPlayer)playerField.get(baubleSlot);
		
		Field slotField = c.getDeclaredField("baubleSlot");
		slotField.setAccessible(true);
		int slot = slotField.getInt(baubleSlot);
		
		c = Class.forName("net.minecraftforge.items.SlotItemHandler");
		Field itemHandlerField = c.getDeclaredField("itemHandler");
		itemHandlerField.setAccessible(true);
		IBaublesItemHandler itemHandler = (IBaublesItemHandler)itemHandlerField.get(baubleSlot);
		
		
		
		return new SlotBaubleModified(player, itemHandler, slot, baubleSlot.xPos, baubleSlot.yPos);
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return super.canTakeStack(player) && !EnchantmentHelper.hasBindingCurse(getStack());
	}

}
