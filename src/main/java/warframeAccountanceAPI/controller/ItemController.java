/**
 * 
 */
package warframeAccountanceAPI.controller;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;
import warframeAccountanceAPI.databaseAccess.ItemRepo;
import warframeAccountanceAPI.domain.Item;

/**
 * @author user
 *
 */
@Slf4j
@RestController
@CrossOrigin
public class ItemController
{
	private ObjectMapper mapper;
	private final ItemRepo itemRepo;

	public ItemController(ItemRepo itemRepo, ObjectMapper mapper)
	{
		this.itemRepo = itemRepo;
		this.mapper = mapper;
	}
	
	
	@GetMapping("api/item")
	public List<Item> getAll ()
	{
		log.info("Get All Item list");
		return itemRepo.findAll();
	}
	
	@PostMapping("api/item/new")
	public Item addOne(@RequestBody Item newItem)
	{
		log.info("Add new Item");
		return itemRepo.save(newItem);
	}
	
	@PutMapping("api/item/all")
	public ObjectNode updateAll(@RequestBody ObjectNode itemList)
	{
		log.info("Save all item");
		ObjectNode returnNode = mapper.createObjectNode();
		try
		{
//			log.info(itemList.toString());
			JsonNode itemListData = itemList.get("itemList");
			for(JsonNode itemNode : itemListData)
			{
				itemRepo.findById(itemNode.get("itemId").asInt()).map(target -> {
					
					JsonNode itemType = itemNode.get("type");
					JsonNode itemQuantity = itemNode.get("quantity");
					JsonNode itemName = itemNode.get("name");
					JsonNode itemEPrice = itemNode.get("eprice");
					JsonNode itemBPrice = itemNode.get("bprice");
					
					if(itemType != null && itemType.isNull() == false)
					{
						target.setType(itemType.asInt());
					}
					if(itemQuantity != null && itemQuantity.isNull() == false)
					{
						target.setQuantity(itemQuantity.asInt());
					}
					if(itemName != null && itemName.isNull() == false)
					{
						target.setName(itemName.asText());
					}
					if(itemEPrice != null && itemEPrice.isNull() == false)
					{
						target.setEPrice(itemEPrice.asInt());
					}
					if(itemBPrice != null && itemBPrice.isNull() == false)
					{
						target.setBPrice(itemBPrice.asInt());
					}
					
					return itemRepo.save(target);
				}).orElseThrow(() -> new NullPointerException());
			}
		}
		catch(Exception e)
		{
			log.info("update Item failed");
			returnNode.put("result", "fail");
			return returnNode;
		}
		
		returnNode.put("result", "success");
		return returnNode;
	}
	
	@DeleteMapping("api/item/remove/{itemId}")
	public Item removeItem(@PathVariable int itemId)
	{
		log.info("Remove item");
		
		Item target = itemRepo.findById(itemId).orElseThrow(() -> new NullPointerException());
		
		itemRepo.delete(target);
		
		return target;
	}
	
	
}
