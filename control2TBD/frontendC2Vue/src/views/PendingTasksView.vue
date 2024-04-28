<script setup lang="ts">
import { onMounted, ref } from "vue";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import Dialog from "primevue/dialog";
import Calendar from "primevue/calendar";
import { parseISO, formatDistanceToNow } from 'date-fns';
import { es } from 'date-fns/locale';
import OrderList from "primevue/orderlist";

const formatRelativeTime = (datetime: string) => {
    // Parse the ISO string into a Date object
    const date = parseISO(datetime);

    // Calculate the distance to now and prepend 'In' or 'Ago'
    const relativeTime = formatDistanceToNow(date, { addSuffix: true, locale: es });

    return relativeTime;
}


const id = localStorage.getItem('idUser');
const token = localStorage.getItem('authToken');

const registerPost = async () => {
    if (token === null) { return }
    try {
        const response = await fetch(`http://localhost:8091/task/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
            body: JSON.stringify(state.value)
        });

        if (!response.ok) {
            throw new Error('Failed to register');
        }

        // Handle token and user data here, e.g., storing the token or redirecting the user
    } catch (error) {
        console.error('Registration error:', error);
    }
};

const errors = ref("")
async function onSubmit(event: Event) {
    event.preventDefault(); // Prevent default form submission
    try {
        state.value.expiredate.toISOString().split('T')[0];
        await registerPost();
        visible.value = false

    } catch (error) {
        errors.value = "Contraseña debe ser de 8 caracters"
        console.error('Validation error:', error);
        // Handle validation errors, e.g., show error messages in the UI
    }
}

async function onSubmitEdit(event: Event) {
    event.preventDefault(); // Prevent default form submission
    try {
        stateEdit.value.expiredate.toISOString().split('T')[0];
        await editTask();
        visibleEdit.value = false

    } catch (error) {
        errors.value = "Contraseña debe ser de 8 caracters"
        console.error('Validation error:', error);
        // Handle validation errors, e.g., show error messages in the UI
    }
}

const state = ref({
    title: '',
    description: '',
    expiredate: new Date(),
    active: true,
    user: { id: id },


})
const visible = ref(false)
const visibleEdit = ref(false)
const editId = ref(-1)
const stateEdit = ref({
    title: '',
    description: '',
    expiredate: new Date(),
})

const tasks = ref()


const finishPost = async (task: any) => {
    try {
        const response = await fetch('http://localhost:8091/task/1/markFinished', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(task)
        });

        if (!response.ok) {
            throw new Error('Failed to register');
        }
        // Handle token and user data here, e.g., storing the token or redirecting the user
    } catch (error) {
        console.error('Registration error:', error);
    }
};


const deleteTask = async (task: any) => {
    try {
        const response = await fetch('http://localhost:8091/task/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(task)
        });

        if (!response.ok) {
            throw new Error('Failed to register');
        }
        // Handle token and user data here, e.g., storing the token or redirecting the user
    } catch (error) {
        console.error('Registration error:', error);
    }
};



const editTask = async () => {
    await fetch(`http://localhost:8091/task/UpdateTitle/${editId.value}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: (stateEdit.value.title)
    });

    await fetch(`http://localhost:8091/task/UpdateDes/${editId.value}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: (stateEdit.value.description)
    });

    await fetch(`http://localhost:8091/task/UpdateDate/${editId.value}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(stateEdit.value.expiredate)
    });
}

async function fetchTasks() {
    const id = localStorage.getItem('idUser');
    if (!token) { return }
    try {
        const response = await fetch(`http://localhost:8091/task/taskByUser/${id}`, { headers: { 'Authorization': token } });
        const result = await response.json()
        tasks.value = result; // Make sure to adjust this according to the actual structure of your response
    } catch (error) {
        console.error('There was an error fetching the user data:', error);
    }
}

const keyword = ref('')
const filterTitle = ref('')
async function fetchTasksKeyword() {
    const id = localStorage.getItem('idUser');
    if (!token) { return }
    try {
        const response = await fetch(`http://localhost:8091/task/${id}/${keyword.value}`, { headers: { 'Authorization': token } });
        const result = await response.json()
        tasks.value = result; // Make sure to adjust this according to the actual structure of your response
    } catch (error) {
        console.error('There was an error fetching the user data:', error);
    }
}

async function fetchTasksTitle() {
    const id = localStorage.getItem('idUser');
    if (!token) { return }
    try {
        const response = await fetch(`http://localhost:8091/task/${id}/${filterTitle.value}`, { headers: { 'Authorization': token } });
        const result = await response.json()
        tasks.value = result; // Make sure to adjust this according to the actual structure of your response
    } catch (error) {
        console.error('There was an error fetching the user data:', error);
    }
}




onMounted(async () => { await fetchTasks() })
</script>
<template>
    <div class=" h-full flex flex-col items-center  align-middle gap-4 mt-12 ">
        <Button label="Crear nueva tarea" @click="visible = true" />

        <div class="space-y-4">
            <div class="space-x-1">
                <InputText type="text" placeholder="Filtrar por palabra clave" v-model="keyword" />
                <Button icon="pi pi-search" @click="fetchTasksKeyword" />
            </div>
            <div class="space-x-1">
                <InputText placeholder="Filtar por titulo" type="text" v-model="filterTitle" />
                <Button icon="pi pi-search" @click="fetchTasksTitle" />
            </div>
        </div>




        <Dialog v-model:visible="visible" modal header="Crear Tarea" :style="{ width: '25rem' }">
            <span class="p-text-secondary block mb-5">Agregue la información</span>
            <div class="flex align-items-center gap-3 mb-3">
                <label for="titulo" class="font-semibold w-6rem">Titulo</label>
                <InputText v-model="state.title" id="titulo" class="flex-auto" autocomplete="off" />
            </div>
            <div class="flex align-items-center gap-3 mb-5">
                <label for="descripcion" class="font-semibold w-6rem">Descripción</label>
                <InputText v-model="state.description" id="descripcion" class="flex-auto" autocomplete="off" />
            </div>

            <div class="flex justify-content-end gap-2  ">
                <label for="expiredate" class="font-semibold w-6rem">Expiración</label>
                <Calendar id="expiredate" v-model="state.expiredate" dateFormat="dd/mm/yy" />
            </div>
            <div class="flex justify-content-end gap-2 mt-4">
                <Button type="button" label="Cancel" severity="secondary" @click="visible = false"></Button>
                <Button type="button" label="Save" @click="onSubmit"></Button>
            </div>
        </Dialog>


        <Dialog v-model:visible="visibleEdit" modal header="Editar Tarea" :style="{ width: '25rem' }">
            <span class="p-text-secondary block mb-5">Agregue la información</span>
            <div class="flex align-items-center gap-3 mb-3">
                <label for="titulo" class="font-semibold w-6rem">Titulo</label>
                <InputText v-model="stateEdit.title" id="titulo" class="flex-auto" autocomplete="off" />
            </div>
            <div class="flex align-items-center gap-3 mb-5">
                <label for="descripcion" class="font-semibold w-6rem">Descripción</label>
                <InputText v-model="stateEdit.description" id="descripcion" class="flex-auto" autocomplete="off" />
            </div>

            <div class="flex justify-content-end gap-2  ">
                <label for="expiredate" class="font-semibold w-6rem">Expiración</label>
                <Calendar id="expiredate" v-model="stateEdit.expiredate" dateFormat="dd/mm/yy" />
            </div>
            <div class="flex justify-content-end gap-2 mt-4">
                <Button type="button" label="Cancel" severity="secondary" @click="visibleEdit = false"></Button>
                <Button type="button" label="Save" @click="onSubmitEdit"></Button>
            </div>
        </Dialog>

        <OrderList v-model="tasks" dataKey="id">
            <template #header>Tareas pendientes</template>

            <template #item="slotProps">
                <div class="flex flex-wrap p-2 align-items-center gap-3" v-if="slotProps.item.active === true">
                    <span class="font-bold">{{ slotProps.item.title }}</span>
                    <div class="flex-1 flex flex-column gap-2">
                        <span class="font-bold">{{ slotProps.item.description }}</span>
                        <div class="flex align-items-center gap-2">
                            <span>{{ formatRelativeTime(slotProps.item.expiredate) }}</span>
                        </div>

                        <div class="flex align-items-center gap-2">
                            <span class="space-x-5 ">
                                <i class="pi pi-check hover:backdrop-brightness-150 rounded-lg "
                                    @click="finishPost(slotProps.item)" />
                                <i class="pi pi-pencil hover:backdrop-brightness-150 rounded-lg"
                                    @click="visibleEdit = true; editId = slotProps.item.id; stateEdit.title = slotProps.item.title; stateEdit.description = slotProps.item.description" />
                                <i class=" pi pi-times hover:backdrop-brightness-150 rounded-lg"
                                    @click="deleteTask(slotProps.item)" />
                            </span>
                        </div>
                    </div>
                </div>
            </template>
        </OrderList>


    </div>
</template>
