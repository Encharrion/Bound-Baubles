package encharrion.boundbaubles.event;

import baubles.common.container.ContainerPlayerExpanded;
import baubles.common.container.SlotBauble;
import encharrion.boundbaubles.container.SlotBaubleModified;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.event.entity.player.PlayerContainerEvent.Open;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SlotReplacer {
	
	@SubscribeEvent
	public static void playerContainerEvent(Open event) {
		Container openedContainer = event.getContainer();
		System.out.println(openedContainer);
		
		if (openedContainer instanceof ContainerPlayerExpanded) 
		{
			
			for(int i = 0; i < openedContainer.inventorySlots.size(); i++) 
			{
				
				Slot currentSlot = openedContainer.inventorySlots.get(i);
				
				if (currentSlot instanceof SlotBauble) 
				{
					try 
					{
						openedContainer.inventorySlots.set(i, SlotBaubleModified.modifyBaubleSlot((SlotBauble)currentSlot));
					} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}

}
