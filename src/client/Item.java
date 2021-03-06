/*
 	OrpheusMS: MapleStory Private Server based on OdinMS
    Copyright (C) 2012 Aaron Weiss

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package client;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Item implements IItem {

	private int id, cashId, sn;
	private byte position;
	private short quantity;
	private int petid = -1;
	private MaplePet pet = null;
	private String owner = "";
	protected List<String> log;
	private byte flag;
	private long expiration = -1;
	private String giftFrom = "";

	public Item(int id, byte position, short quantity) {
		this.id = id;
		this.position = position;
		this.quantity = quantity;
		this.log = new LinkedList<String>();
		this.flag = 0;
	}

	public Item(int id, byte position, short quantity, int petid) {
		this.id = id;
		this.position = position;
		this.quantity = quantity;
		this.petid = petid;
		if (petid > -1)
			this.pet = MaplePet.loadFromDb(id, position, petid);
		this.flag = 0;
		this.log = new LinkedList<String>();
	}

	public IItem copy() {
		Item ret = new Item(id, position, quantity, petid);
		ret.flag = flag;
		ret.owner = owner;
		ret.expiration = expiration;
		ret.log = new LinkedList<String>(log);
		return ret;
	}

	public void setPosition(byte position) {
		this.position = position;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	@Override
	public int getItemId() {
		return id;
	}

	@Override
	public int getCashId() {
		if (cashId == 0) {
			cashId = new Random().nextInt(Integer.MAX_VALUE) + 1;
		}
		return cashId;
	}

	@Override
	public byte getPosition() {
		return position;
	}

	@Override
	public short getQuantity() {
		return quantity;
	}

	@Override
	public byte getType() {
		if (getPetId() > -1) {
			return IItem.PET;
		}
		return IItem.ITEM;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public int getPetId() {
		return petid;
	}

	@Override
	public void setPetId(int id) {
		this.petid = id;
	}

	@Override
	public int compareTo(IItem other) {
		if (this.id < other.getItemId()) {
			return -1;
		} else if (this.id > other.getItemId()) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Item: " + id + " quantity: " + quantity;
	}

	public List<String> getLog() {
		return Collections.unmodifiableList(log);
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte b) {
		this.flag = b;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expire) {
		this.expiration = expire;
	}

	public int getSN() {
		return sn;
	}

	public void setSN(int sn) {
		this.sn = sn;
	}

	public String getGiftFrom() {
		return giftFrom;
	}

	public void setGiftFrom(String giftFrom) {
		this.giftFrom = giftFrom;
	}

	public MaplePet getPet() {
		return pet;
	}
}
