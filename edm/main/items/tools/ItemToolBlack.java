package EDM.edm.main.items.tools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import EDM.edm.main.handler.enums.EnumToolMaterials;

public class ItemToolBlack extends Item{

	private Block[] blocksEffectiveAgainst;
	protected float efficiencyOnProperMaterial = 4.0F;
	private float damageVsEntity;
	protected EnumToolMaterials toolMaterial;
	
	protected ItemToolBlack(int par1, int par2, EnumToolMaterials par3EnumToolMaterial, Block[] par4ArrayOfBlock)
	{
         super(par1);
         this.toolMaterial = par3EnumToolMaterial;
         this.blocksEffectiveAgainst = par4ArrayOfBlock;
         this.maxStackSize = 1;
         this.setMaxDamage(par3EnumToolMaterial.getMaxUses());
         this.efficiencyOnProperMaterial = par3EnumToolMaterial.getEfficiencyOnProperMaterial();
         this.damageVsEntity = par2 + par3EnumToolMaterial.getDamageVsEntity();
         this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block){
         for (int var3 = 0; var3 < this.blocksEffectiveAgainst.length; ++var3){
                 if (this.blocksEffectiveAgainst[var3] == par2Block){
                         return this.efficiencyOnProperMaterial;
                 }
         }
         return 1.0F;
	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
         par1ItemStack.damageItem(2, par3EntityLiving);
         return true;
	}
	
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving){
         if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D){
                 par1ItemStack.damageItem(1, par7EntityLiving);
         }
         return true;
	}
	
	public float getDamageVsEntity(Entity par1Entity)
	{
         return this.damageVsEntity;
	}
	
	public boolean isFull3D()
	{
         return true;
	}
	
	public int getItemEnchantability()
	{
         return this.toolMaterial.getEnchantability();
	}
	
	public String getToolMaterialName()
	{
         return this.toolMaterial.toString();
	}
}