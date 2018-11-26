/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { AbnahmeprotokollDetailComponent } from 'app/entities/abnahmeprotokoll/abnahmeprotokoll-detail.component';
import { Abnahmeprotokoll } from 'app/shared/model/abnahmeprotokoll.model';

describe('Component Tests', () => {
    describe('Abnahmeprotokoll Management Detail Component', () => {
        let comp: AbnahmeprotokollDetailComponent;
        let fixture: ComponentFixture<AbnahmeprotokollDetailComponent>;
        const route = ({ data: of({ abnahmeprotokoll: new Abnahmeprotokoll(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [AbnahmeprotokollDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AbnahmeprotokollDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AbnahmeprotokollDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.abnahmeprotokoll).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
