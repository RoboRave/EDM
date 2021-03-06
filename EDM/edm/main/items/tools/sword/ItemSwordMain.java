package EDM.edm.main.items.tools.sword;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import EDM.edm.main.handler.enums.EnumToolMaterials;
import EDM.edm.main.items.ItemCore;

import com.google.common.collect.Multimap;

public class ItemSwordMain extends ItemCore
{
	
	private float weaponDamage;
	private final EnumToolMaterials toolMaterials;
	
	public ItemSwordMain(int par1, EnumToolMaterials par2EnumToolMaterials, String name)
	{
         super(par1, name);
         this.toolMaterials = par2EnumToolMaterials;
         this.maxStackSize = 1;
         this.setMaxDamage(par2EnumToolMaterials.getMaxUses());
         this.setCreativeTab(CreativeTabs.tabCombat);
         this.weaponDamage = 4.0F + par2EnumToolMaterials.getDamageVsEntity();
         this.setTextureName("mod_beta"+":"+name);
	}
	
	public float func_82803_g()
	{
         return this.toolMaterials.getDamageVsEntity();
	}
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        if (par2Block.blockID == Block.web.blockID)
        {
            return 15.0F;
        }
        else
        {
            Material material = par2Block.blockMaterial;
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.pumpkin ? 1.0F : 1.5F;
        }
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
        if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(2, par7EntityLivingBase);
        }

        return true;
    }

	
	public float getDamageVsEntity(Entity par1Entity)
	{
         return this.weaponDamage;
	}
	
	public boolean isFull3D()
	{
         return true;
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
         return EnumAction.block;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
         return 72000;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

	public boolean canHarvestBlock(Block par1Block)
	{
         return par1Block.blockID == Block.web.blockID;
	}
	
	public int getItemEnchantability()
	{
         return this.toolMaterials.getEnchantability();
	}

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.weaponDamage, 0));
        return multimap;
    }
	public String getToolBlackName()
	{
         return this.toolMaterials.toString();
	}
}