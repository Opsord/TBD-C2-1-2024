<script setup lang="ts">
import { z } from "zod"
import type { FormSubmitEvent } from "#ui/types"
import { format } from 'date-fns'


const schema = z.object({
    title: z.string().min(3, 'Ingrese un titulo'),
    description: z.string().min(3, 'Ingrese descripción'),
    date: z.date().refine((date) => {
        return date > new Date();
    }, {
        message: "Debe ser en el futuro"
    })
})

type Schema = z.infer<typeof schema>


const date = new Date()
const state = reactive({
    title: undefined,
    description: undefined,
    date: date,


})

async function onSubmit(event: FormSubmitEvent<Schema>) {
    console.log(event.data)

}


</script>

<template>
    <div class=" flex flex-col justify-center items-center flex-grow p-8">
        <UForm :schema="schema" :state="state" class="space-y-4" @submit="onSubmit">
            <UFormGroup label="Titulo" name="title">
                <UInput v-model="state.title" />
            </UFormGroup>

            <UFormGroup label="Descripción" name="description">
                <UInput v-model="state.description" />
            </UFormGroup>

            <UFormGroup label="Fecha de expiración" name="expireDate">
                <UPopover :popper="{ placement: 'bottom-start' }">
                    <UButton icon="i-heroicons-calendar-days-20-solid" :label="format(date, 'yyy-MMM-d')" />

                    <template #panel="{ close }">
                        <DatePicker v-model="state.date" is-required @close="close" />
                    </template>
                </UPopover>

            </UFormGroup>

            <UButton label="Enviar" type="submit">
            </UButton>
        </UForm>
    </div>
</template>