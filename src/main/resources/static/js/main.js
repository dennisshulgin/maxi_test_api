Vue.component('sum-form', {
    data: function () {
        return {
            dateSale: '',
            sum: 0,
        }
    },
    template: '<div class="container mt-3">' +
        '        <div class="mb-3 row">' +
        '            <label for="staticSum" class="col-sm-1 col-form-label">Сумма</label>' +
        '            <div class="col-sm-3">' +
        '                <input type="text" readonly class="form-control-plaintext" id="staticSum" v-model="sum">' +
        '            </div>' +
        '        </div>' +
        '        <div class="mb-3 row">' +
        '            <label for="date" class="col-sm-1 col-form-label">Дата</label>' +
        '            <div class="col-sm-3">' +
        '                <input type="date" class="form-control" id="date" v-model="dateSale">' +
        '            </div>' +
        '        </div>' +
        '        <button type="button" class="btn btn-success" @click="getSum">Рассчитать</button>' +
        '    </div>',
    methods: {
        getSum: function () {
            let longDate = Date.parse(this.dateSale);
            let request = '/api/sales.sum?date=' + longDate;
            axios
                .get(request)
                .then(response => (this.sum = response.data.sum));
        }
    }
})

Vue.component('top-form', {
    data: function () {
        return {
            products: [],
            card: '',
            limit: 3
        }
    },
    template: '<div class="container mt-3">' +
        '        <div class="mb-3 row">' +
        '            <label for="card" class="col-sm-1 col-form-label">Номер карты</label>' +
        '            <div class="col-sm-3">' +
        '                <input type="text" class="form-control" id="card" v-model="card">' +
        '            </div>' +
        '        </div>' +
        '        <button type="button" class="btn btn-success" @click="getTop">Найти товары</button>' +
        '        <table class="table">' +
        '            <thead>' +
        '            <tr>' +
        '                <th scope="col">#</th>' +
        '                <th scope="col">Наименование продукта</th>' +
        '                <th scope="col">Цена продукта</th>' +
        '            </tr>' +
        '            </thead>' +
        '            <tbody>' +
        '            <tr v-for="product in products">' +
        '                <th scope="row">{{ product.productCode }}</th>' +
        '                <td>{{ product.productName }}</td>' +
        '                <td>{{ product.productPrice }}</td>' +
        '            </tr>' +
        '            </tbody>' +
        '        </table>' +
        '    </div>',
    methods: {
        getTop: function () {
            let request = '/api/sales.top?card=' + this.card + '&limit=' + this.limit;
            axios
                .get(request)
                .then(response => (this.products = response.data));
        }
    }
})

var app = new Vue({
    el: '#app',
    data: {
        products: []
    },
    template: '<div><sum-form /><top-form /></div>'
})