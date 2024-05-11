import axios from 'axios';
import Helper from './Helper';
import {ref} from "vue";
import {shelfData} from "@/services/ShelfItemService";

const PRODUCT_API_BASE_URL = Helper.getHost() + '/api/shopping';

export const shoppingData = ref({
    shoppingItems: []
});

class ShoppingItemService {

    getAll() {
        return axios.get(PRODUCT_API_BASE_URL + "/all");
    }

    add(shelfItem) {
        return axios.post(PRODUCT_API_BASE_URL + "/add", shelfItem);
    }

    edit(shelfItem) {
        return axios.post(PRODUCT_API_BASE_URL + "/edit", shelfItem);
    }

    delete(id) {
        return axios.delete(PRODUCT_API_BASE_URL + "/delete", { data: id});
    }

    processMessage(message) {
        shoppingData.value.shoppingItems = Object.values(message);
        shoppingData.value.shoppingItems.forEach(item => {
            item.shelfItem = shelfData.value.shelfItems.find(itm => itm.id === item.shelfItemId);
        })
    }

}

export default new ShoppingItemService();